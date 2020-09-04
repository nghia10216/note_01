package com.sun.note_01.data.source

import com.sun.note_01.data.source.remote.OnFetchDataJsonListener

class UserRepository private constructor(
    private val remote: UserDataSource.Remote,
    private val local: UserDataSource.Local
) : UserDataSource.Local, UserDataSource.Remote {

    override fun login(email: String, password: String, listener: OnFetchDataJsonListener<Int>) {
        remote.login(email, password, listener)
    }

    override fun register(email: String, password: String, listener: OnFetchDataJsonListener<Int>) {
        remote.register(email, password, listener)
    }

    override fun getUserIdLocal() = local.getUserIdLocal()

    override fun setUserIdLocal(userId: Int) {
        local.setUserIdLocal(userId)
    }

    override fun isRememberAccount() = local.isRememberAccount()

    override fun setRememberAccount(isRemember: Boolean) {
        local.setRememberAccount(isRemember)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(local: UserDataSource.Local, remote: UserDataSource.Remote) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(local = local, remote = remote).also { instance = it }
            }
    }
}
