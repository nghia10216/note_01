package com.sun.note_01.data.source.remote.fetchjson

import android.os.AsyncTask
import com.sun.note_01.data.source.remote.OnFetchDataJsonListener
import com.sun.note_01.untils.Constant
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATION", "UNCHECKED_CAST")
class GetJsonFromUrl<T>(
    private val listener: OnFetchDataJsonListener<T>,
    private val keyEntity: String
) : AsyncTask<String?, Void?, DataResponse?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): DataResponse? {
        var dataResponse = ""
        val dataType = params[POSITION_DATA_TYPE_RESPONSE]
        try {
            val method = params[POSITION_REQUEST_METHOD]
            val url = URL(params[POSITION_URL])
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                readTimeout = TIME_OUT
                requestMethod = params[POSITION_REQUEST_METHOD]
                setRequestProperty("Content-Type", "application/json; utf-8")
                doInput = true
                doOutput = method != Constant.METHOD_GET
                connect()
            }
            if (method == Constant.METHOD_PUT || method == Constant.METHOD_POST) {
                val email = params[POSITION_EMAIL]
                val password = params[POSITION_PASSWORD]
                val jsonObject = ParseJson().createJson(email!!, password!!)
                setPostRequestContent(urlConnection, jsonObject)
            }
            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val stream = BufferedInputStream(urlConnection.inputStream)
                dataResponse = readStream(stream)
                return dataType?.let { DataResponse(dataResponse, it) }
            }
        } catch (e: Exception) {
            exception = e
        }
        return dataType?.let { DataResponse(dataResponse, it) }
    }

    private fun readStream(stream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(stream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { str -> stringBuilder.append(str) }
        return stringBuilder.toString()
    }

    private fun setPostRequestContent(conn: HttpURLConnection, jsonObject: JSONObject) {
        val os = conn.outputStream as OutputStream
        BufferedWriter(OutputStreamWriter(os, "UTF-8")).run {
            write(jsonObject.toString())
            flush()
            close()
        }
        os.close()
    }

    override fun onPostExecute(dataResponse: DataResponse?) {
        super.onPostExecute(dataResponse)
        when (dataResponse?.dataType) {
            DataTypeResponse.Login.name, DataTypeResponse.Register.name -> when {
                dataResponse.data.isEmpty() -> listener.onError(exception)
                dataResponse.data.toInt() == Constant.RESPONSE_FAILED -> listener.onError(exception)
                dataResponse.data.toInt() != Constant.RESPONSE_FAILED -> listener.onSuccess(
                    dataResponse.data.toInt() as T
                )
            }
        }
    }

    companion object {
        private const val TIME_OUT = 15000
        private const val POSITION_URL = 0
        private const val POSITION_REQUEST_METHOD = 1
        private const val POSITION_DATA_TYPE_RESPONSE = 2
        private const val POSITION_EMAIL = 3
        private const val POSITION_PASSWORD = 4
    }
}

enum class DataTypeResponse(private val dataType: String) {
    Login("Login"), Register("Register")
}

data class DataResponse(val data: String, val dataType: String)
