package com.sun.note_01.screen.login.fragment.childregister

import com.sun.note_01.data.source.UserRepository
import com.sun.note_01.data.source.remote.OnFetchDataJsonListener
import com.sun.note_01.untils.Constant.isValidEmail
import com.sun.note_01.untils.Constant.isValidPassword

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val view: RegisterContract.View
) : RegisterContract.Presenter {

    override fun register(email: String, password: String, confirmPassword: String) {
        if (email.isValidEmail() && password.isValidPassword() && (password == confirmPassword)) {
            userRepository.register(email, password, object : OnFetchDataJsonListener<Int> {
                override fun onSuccess(data: Int) {
                    view.onRegisterSuccess()
                }

                override fun onError(exception: Exception?) {
                    view.onError(exception)
                }
            })
        } else {
            view.onErrorValidate()
        }
    }
}
