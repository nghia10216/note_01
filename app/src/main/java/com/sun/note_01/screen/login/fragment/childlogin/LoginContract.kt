package com.sun.note_01.screen.login.fragment.childlogin

interface LoginContract {

    interface Presenter {

        fun receiverData(email: String, password: String)
    }

    interface View {

        fun onErrorValidate()
    }
}
