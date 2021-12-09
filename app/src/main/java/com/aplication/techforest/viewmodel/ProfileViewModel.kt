package com.aplication.techforest.viewmodel

import androidx.lifecycle.ViewModel
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.repository.PlantRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PlantRepository
) : ViewModel(){

    suspend fun getUserProfile(
        userId: Int
    ): Resource<UserResponse> {
        return repository.getUser(userId = userId)
    }
}

