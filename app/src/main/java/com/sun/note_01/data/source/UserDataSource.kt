package com.sun.note_01.data.source

import com.sun.note_01.data.source.remote.OnFetchDataJsonListener

interface UserDataSource {

    interface Remote {
        fun login(
            email: String,
            password: String, listener: OnFetchDataJsonListener<Int>
        )
    }

    interface Local {
        fun getUserIdLocal(): Int
        fun setUserIdLocal(userId: Int)
        fun isRememberAccount(): Boolean
        fun setRememberAccount(isRemember: Boolean)
    }
}
