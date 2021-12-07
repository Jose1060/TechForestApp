package com.aplication.techforest.repository

import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.model.Device.ValveDeviceResponse
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DeviceRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getDevice(id: Int): Resource<DeviceResponse> {
        val response = try {
            apiInterface.getDeviceData(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getUser(correo: String): Resource<UserResponse> {
        val response = try {
            apiInterface.getUserMail(correo)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getOptions(id: Int): Resource<OptionDeviceResponse> {
        val response = try {
            apiInterface.getOptionsDevice(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getValve(id: Int): Resource<ValveDeviceResponse> {
        val response = try {
            apiInterface.getValveDevice(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getDeviceUser(id: Int): Resource<DeviceResponse> {
        val response = try {
            apiInterface.getDevicesUser(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}