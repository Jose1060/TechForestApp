package com.aplication.techforest.model

import androidx.annotation.StringRes

data class LoginState(
    val email: String = "",
    val password: String = "",
    val userId: Int = 0,
    val successLogin: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Any? = null
)
