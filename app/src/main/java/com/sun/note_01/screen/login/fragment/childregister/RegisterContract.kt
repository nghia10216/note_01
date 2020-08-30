package com.sun.note_01.screen.login.fragment.childregister

interface RegisterContract {

    interface Presenter {

        fun receiverData(email: String, password: String, confirmPassword: String)
    }

    interface View {

        fun onErrorValidate()
    }
}
