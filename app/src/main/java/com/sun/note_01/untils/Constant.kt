package com.sun.note_01.untils

import java.util.regex.Pattern

object Constant {

    const val BASE_URL = "http://openedu35-dn.somee.com/api/"
    const val LOGIN_PATH = "user/login"
    const val REGISTER_PATH = "user/registerUser"
    const val RESPONSE_FAILED = -1
    const val METHOD_PUT = "PUT"
    const val METHOD_POST = "POST"
    const val METHOD_GET = "GET"

    private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}$"
    )

    fun String.isValidEmail(pattern: Pattern = EMAIL_ADDRESS_PATTERN): Boolean =
        pattern.matcher(this).matches()

    fun String.isValidPassword(pattern: Pattern = PASSWORD_PATTERN): Boolean =
        pattern.matcher(this).matches()
}
