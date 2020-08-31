package com.sun.note_01.screen.login.fragment.childlogin

import java.lang.Exception

interface LoginContract {

    interface Presenter {
        fun setRememberAccount(isRemember: Boolean)
        fun login(email: String, password: String)
    }

    interface View {
        fun onErrorValidate()
        fun onLoginSuccess()
        fun onError(exception: Exception?)
    }
}
