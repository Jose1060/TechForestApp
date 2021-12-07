package com.aplication.techforest.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.R
import com.aplication.techforest.model.LoginState
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    var userList = mutableStateOf<List<UserResponse>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)
    val state: MutableState<LoginState> = mutableStateOf(LoginState())
    /*
        fun login(email: String, password: String) {
            viewModelScope.launch {
                state.value = state.value.copy(displayProgressBar = true)
                val result = repository.getUser(usuario = email)
                val userEntries = result.data!!
                val errorMessage = if (email.isBlank() || password.isBlank()) {
                    R.string.error_input_empty
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    R.string.error_not_a_valid_email
                } else if (email != userEntries.usuario || password != userEntries.clave) {
                    Log.d(
                        "TAG",
                        "${userEntries.usuario}, ${userEntries.clave}, validar = ${email}, ${password}"
                    )
                    R.string.error_invalid_credentials
                } else if (email == userEntries.usuario || password == userEntries.clave) {
                    delay(3000)
                    Log.d(
                        "TAG",
                        "${userEntries.usuario}, ${userEntries.clave}, validar = ${email}, ${password}"
                    )
                    state.value = state.value.copy(email = email, password = password)
                    state.value = state.value.copy(displayProgressBar = false)
                    state.value = state.value.copy(successLogin = true)
                } else null
                errorMessage?.let {
                    state.value = state.value.copy(errorMessage = it)
                    state.value = state.value.copy(displayProgressBar = false)
                    return@launch
                }
            }
        }
    */

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
    fun login(email: String, password: String) {
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            when (val result = repository.getUser(correo = email)) {
                is Resource.Success -> {
                    val errorMessage = if (email.isBlank() || password.isBlank()) {
                        R.string.error_input_empty
                    } else if (result.data != null) {
                        val userEntries = result.data
                        Log.d("404", "$userEntries")
                        Log.d(
                            "TAG",
                            "${userEntries.correo}, ${userEntries.contraseña}, validar = ${email}, ${password}"
                        )
                        if (email != userEntries.correo || password != userEntries.contraseña) {
                            R.string.error_invalid_credentials
                        } else if (email == userEntries.correo || password == userEntries.contraseña) {
                            delay(3000)
                            Log.d("Id userLoginViewModel", "${userEntries.id}")
                            state.value = state.value.copy(userId = userEntries.id)
                            state.value = state.value.copy(displayProgressBar = false)
                            state.value = state.value.copy(successLogin = true)
                        } else null
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        R.string.error_not_a_valid_email
                    } else {
                        R.string.error_invalid_credentials
                    }
                    errorMessage?.let {
                        state.value = state.value.copy(errorMessage = it)
                        state.value = state.value.copy(displayProgressBar = false)
                        return@launch
                    }
                }
                is Resource.Error -> {
                    if (result.message != "Resource error : An unknown error occurred: HTTP 404 Not Found") {
                        state.value =
                            state.value.copy(errorMessage = R.string.error_invalid_credentials)
                        state.value = state.value.copy(displayProgressBar = false)
                        Log.d(
                            "Error",
                            "Resource error : ${result.message}"
                        )
                    } else {
                        state.value = state.value.copy(errorMessage = result.message)
                        loadError.value = result.message
                        isLoading.value = false
                        state.value = state.value.copy(displayProgressBar = false)
                        Log.d(
                            "Error",
                            "Resource error : ${result.message}"
                        )
                    }
                }
                else -> {
                    Log.d(
                        "Else",
                        "${result.data?.correo}, ${result.data?.contraseña}, validar = $email, $password"
                    )
                }
            }
        }
    }
}