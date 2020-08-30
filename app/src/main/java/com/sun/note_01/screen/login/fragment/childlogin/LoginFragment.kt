package com.sun.note_01.screen.login.fragment.childlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.note_01.R
import kotlinx.android.synthetic.main.fragment_login_child.*

class LoginFragment : Fragment(), LoginContract.View {

    private var loginPresenter: LoginContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        loginButton.setOnClickListener {
            val username = usernameTextInput?.editText?.text.toString()
            val password = passwordTextInput?.editText?.text.toString()
            loginPresenter?.receiverData(username, password)
        }
    }

    private fun initData() {
        loginPresenter = LoginPresenter(this)
    }

    override fun onErrorValidate() {
        errorTextView.text = getString(R.string.error_syntax)
    }
}
