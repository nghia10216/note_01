package com.sun.note_01.data.source.local.prefs

interface PreferenceHelper {
    fun getUserIdLocal(): Int
    fun setUserIdLocal(userId: Int)
    fun isRememberAccount(): Boolean
    fun setRememberAccount(isRemember: Boolean)
}
