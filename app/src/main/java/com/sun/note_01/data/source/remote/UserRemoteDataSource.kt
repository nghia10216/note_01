package com.sun.note_01.data.source.remote

import com.sun.note_01.data.source.UserDataSource
import com.sun.note_01.data.source.remote.fetchjson.DataTypeResponse
import com.sun.note_01.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.note_01.untils.Constant

class UserRemoteDataSource : UserDataSource.Remote {

    override fun login(email: String, password: String, listener: OnFetchDataJsonListener<Int>) {
        GetJsonFromUrl(listener, "").execute(
            Constant.BASE_URL + Constant.LOGIN_PATH,
            Constant.METHOD_PUT,
            DataTypeResponse.Login.name,
            email,
            password
        )
    }

    override fun register(email: String, password: String, listener: OnFetchDataJsonListener<Int>) {
        GetJsonFromUrl(listener, "").execute(
            Constant.BASE_URL + Constant.REGISTER_PATH,
            Constant.METHOD_POST,
            DataTypeResponse.Register.name,
            email,
            password
        )
    }

    private object Holder {
        val INSTANCE = UserRemoteDataSource()
    }

    companion object {
        val INSTANCE: UserRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
