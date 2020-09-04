package com.sun.note_01.data.source.remote.fetchjson

import org.json.JSONObject

class ParseJson {

    fun createJson(email: String, password: String) = JSONObject().apply {
        put(EMAIL, email)
        put(PASSWORD, password)
    }

    private companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}
