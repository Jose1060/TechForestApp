package com.aplication.techforest.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel(){

    suspend fun getUserProfile(
        userId: Int
    ): Resource<UserResponse> {
        return repository.getUser(userId = userId)
    }
}

