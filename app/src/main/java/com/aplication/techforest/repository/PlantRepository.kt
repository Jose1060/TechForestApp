package com.aplication.techforest.repository

import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.model.Device.ValveDeviceResponse
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class PlantRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getDevice(id: Int): Resource<DeviceResponse> {
        val response = try {
            apiInterface.getDevice(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getUserMail(correo: String): Resource<UserResponse> {
        val response = try {
            apiInterface.getUserMail(correo)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getUser(userId: Int): Resource<UserResponse> {
        val response = try {
            apiInterface.getUser(userId)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getOptionsDevice(id: Int): Resource<OptionDeviceResponse> {
        val response = try {
            apiInterface.getOptionsDevice(id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getValveDevice(id: Int): Resource<ValveDeviceResponse> {
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

    suspend fun getOptions(id: Int):Resource<OptionDeviceResponse>{
        val response = try {
            apiInterface.getOptions(id)
        } catch (e:Exception){
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }








    suspend fun updateOptionsDevice(
        id: Int,
        options: OptionDeviceResponse
    ): Response<OptionDeviceResponse> {
        return apiInterface.updateOptions(id, options)
    }

    suspend fun updateValvesDevice(
        id: Int,
        valveDeviceResponse: ValveDeviceResponse
    ): Resource<ValveDeviceResponse> {
        val response = try {
            apiInterface.updateValve(id = id, valveDeviceResponse = valveDeviceResponse)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: {$e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun updateDevice(
        id: Int,
        deviceResponse: DeviceResponse
    ): Resource<DeviceResponse> {
        val response = try {
            apiInterface.updateDevice(id = id, deviceResponse = deviceResponse)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun updateUser(
        id: Int,
        userResponse: UserResponse
    ): Resource<UserResponse> {
        val response = try {
            apiInterface.updateUser(id = id, userResponse = userResponse)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}




