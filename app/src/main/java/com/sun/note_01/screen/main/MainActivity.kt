package com.sun.note_01.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sun.note_01.R
import com.sun.note_01.screen.login.LoginParentFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(LoginParentFragment())
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frame_layout, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
