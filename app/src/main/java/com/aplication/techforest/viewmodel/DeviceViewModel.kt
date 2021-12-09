package com.aplication.techforest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.repository.PlantRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val repository: PlantRepository
) : ViewModel() {

    var deviceList = mutableStateOf<List<DeviceResponse>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    suspend fun getDevicesData(userId : Int) : Resource<DeviceResponse> {
        return repository.getDeviceUser(userId)
    }
}