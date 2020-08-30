package com.sun.note_01.screen.login.fragment.childlogin

import com.sun.note_01.untils.Constant.isValidEmail
import com.sun.note_01.untils.Constant.isValidPassword

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun receiverData(email: String, password: String) {
        if (email.isValidEmail() && password.isValidPassword()) {

        } else {
            view.onErrorValidate()
        }
    }
}
