package com.sun.note_01.screen.login.fragment.childregister

import java.lang.Exception

interface RegisterContract {

    interface Presenter {
        fun register(email: String, password: String, confirmPassword: String)
    }

    interface View {

        fun onRegisterSuccess()
        fun onError(exception: Exception?)
        fun onErrorValidate()
    }
}
