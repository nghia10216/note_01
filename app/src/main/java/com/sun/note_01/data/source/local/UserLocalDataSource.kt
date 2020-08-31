package com.sun.note_01.data.source.local

import com.sun.note_01.data.source.UserDataSource
import com.sun.note_01.data.source.local.prefs.AppPreferencesHelper

class UserLocalDataSource private constructor(
    private val pref: AppPreferencesHelper
) : UserDataSource.Local {

    override fun getUserIdLocal() = pref.getUserIdLocal()

    override fun setUserIdLocal(userId: Int) {
        pref.setUserIdLocal(userId)
    }

    override fun isRememberAccount() = pref.isRememberAccount()

    override fun setRememberAccount(isRemember: Boolean) {
        pref.setRememberAccount(isRemember)
    }

    companion object {
        private var instance: UserLocalDataSource? = null

        fun getInstance(preferencesHelper: AppPreferencesHelper) =
            instance ?: synchronized(this) {
                instance ?: UserLocalDataSource(preferencesHelper).also { instance = it }
            }
    }
}
