package com.sun.note_01.screen.login.fragment.childregister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.note_01.R
import kotlinx.android.synthetic.main.fragment_register_child.*

class RegisterFragment : Fragment(), RegisterContract.View {

    private var registerPresenter: RegisterContract.Presenter? = null

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
            val username = usernameTextInput.editText?.text.toString()
            val password = passwordTextInput.editText?.text.toString()
            val confirmPassword = confirmPasswordTextInput.editText?.text.toString()
            registerPresenter?.register(username, password, confirmPassword)
        }
    }

    private fun initData() {
        registerPresenter = RegisterPresenter(this)
    }

    override fun onErrorValidate() {
        errorTextView.text = getString(R.string.error_syntax)
    }
}
