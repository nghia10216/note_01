package com.sun.note_01.screen.login.fragment.childlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.note_01.R
import com.sun.note_01.data.source.UserRepository
import com.sun.note_01.data.source.local.UserLocalDataSource
import com.sun.note_01.data.source.local.prefs.AppPreferencesHelper
import com.sun.note_01.data.source.remote.UserRemoteDataSource
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
            loginPresenter?.setRememberAccount(rememberCheckBox.isChecked)
            loginProgressBar.visibility = View.VISIBLE
            val username = usernameTextInput?.editText?.text.toString()
            val password = passwordTextInput?.editText?.text.toString()
            loginPresenter?.login(username, password)
        }
    }

    private fun initData() {
        loginPresenter = context?.let {
            LoginPresenter(
                UserRepository.getInstance(
                    UserLocalDataSource.getInstance(AppPreferencesHelper.getInstance(it)),
                    UserRemoteDataSource.INSTANCE
                ),
                this
            )
        }
    }

    override fun onErrorValidate() {
        loginProgressBar.visibility = View.INVISIBLE
        errorTextView.text = getString(R.string.error_syntax)
    }

    override fun onLoginSuccess() {
        loginProgressBar.visibility = View.INVISIBLE
    }

    override fun onError(exception: Exception?) {
        loginProgressBar.visibility = View.INVISIBLE
        if (exception == null) {
            context.let { Toast.makeText(it, R.string.login_failed, Toast.LENGTH_SHORT).show() }
        } else {
            context.let { Toast.makeText(it, R.string.login_error, Toast.LENGTH_SHORT).show() }
        }
    }
}
