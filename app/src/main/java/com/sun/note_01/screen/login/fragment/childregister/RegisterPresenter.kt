package com.sun.note_01.screen.login.fragment.childregister

import com.sun.note_01.untils.Constant.isValidEmail
import com.sun.note_01.untils.Constant.isValidPassword

class RegisterPresenter(private val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun receiverData(email: String, password: String, confirmPassword: String) {
        if (email.isValidEmail() && password.isValidPassword() && (password == confirmPassword)) {

        } else {
            view.onErrorValidate()
        }
    }
}
