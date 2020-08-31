package com.sun.note_01.data.source.local.prefs

import android.content.Context

class AppPreferencesHelper internal constructor(
    context: Context
) : PreferenceHelper {

    private val preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    override fun getUserIdLocal() = preferences.getInt(PREF_USER_ID, -1)

    override fun setUserIdLocal(userId: Int) {
        preferences.edit().putInt(PREF_USER_ID, userId).apply()
    }

    override fun isRememberAccount() = preferences.getBoolean(PREF_CHECK_REMEMBER, false)

    override fun setRememberAccount(isRemember: Boolean) {
        preferences.edit().putBoolean(PREF_CHECK_REMEMBER, isRemember).apply()
    }

    companion object {
        const val PREF_USER_ID = "PREF_USER_ID"
        const val PREF_FILE_NAME = "PREF_FILE_NAME"
        const val PREF_CHECK_REMEMBER = "PREF_CHECK_REMEMBER"
        private var instance: AppPreferencesHelper? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: AppPreferencesHelper(context).also { instance = it }
        }
    }
}
