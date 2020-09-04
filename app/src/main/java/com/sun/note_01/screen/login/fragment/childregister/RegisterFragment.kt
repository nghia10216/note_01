package com.sun.note_01.screen.login.fragment.childregister

import android.app.ProgressDialog
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
import kotlinx.android.synthetic.main.fragment_register_child.*
import kotlinx.android.synthetic.main.fragment_register_child.errorTextView
import kotlinx.android.synthetic.main.fragment_register_child.passwordTextInput
import kotlinx.android.synthetic.main.fragment_register_child.usernameTextInput
import java.lang.Exception

class RegisterFragment : Fragment(), RegisterContract.View {

    private var registerPresenter: RegisterContract.Presenter? = null
    private var progressDialog: ProgressDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        registerButton.setOnClickListener {
            progressDialog?.show()
            val username = usernameTextInput.editText?.text.toString()
            val password = passwordTextInput.editText?.text.toString()
            val confirmPassword = confirmPasswordTextInput.editText?.text.toString()
            registerPresenter?.register(username, password, confirmPassword)
        }
    }

    private fun initData() {
        progressDialog = ProgressDialog(context)
        registerPresenter = context?.let {
            RegisterPresenter(
                UserRepository.getInstance(
                    UserLocalDataSource.getInstance(
                        AppPreferencesHelper.getInstance(it)
                    ),
                    UserRemoteDataSource.INSTANCE
                ),
                this
            )
        }
    }

    override fun onErrorValidate() {
        progressDialog?.dismiss()
        errorTextView.text = getString(R.string.error_syntax)
    }

    override fun onRegisterSuccess() {
        progressDialog?.dismiss()
    }

    override fun onError(exception: Exception?) {
        progressDialog?.dismiss()
        if (exception == null) {
            context.let { Toast.makeText(it, R.string.register_failed, Toast.LENGTH_SHORT).show() }
        } else {
            context.let { Toast.makeText(it, R.string.login_error, Toast.LENGTH_SHORT).show() }
        }
    }
}
