package com.sun.note_01.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.note_01.R
import com.sun.note_01.screen.login.adapter.LoginViewpagerAdapter
import kotlinx.android.synthetic.main.fragment_login_parent.*

class LoginParentFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPagerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_parent, container, false)
    }

    private fun initViewPagerAdapter() {
        loginViewpager.adapter = context?.let { LoginViewpagerAdapter(childFragmentManager, it) }
        loginTabLayout.setupWithViewPager(loginViewpager)
    }
}
