package com.sun.note_01.untils

import java.util.regex.Pattern

object Constant {

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
