package com.aplication.techforest.repository

import com.aplication.techforest.model.TimeResponse
import com.aplication.techforest.network.time.TimeApiInterface
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TimeRepository @Inject constructor(
    private val NasaApiInterface: TimeApiInterface
) {
    suspend fun getTime(city: String): Resource<TimeResponse> {
        val response = try {
            NasaApiInterface.getTime(city)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}