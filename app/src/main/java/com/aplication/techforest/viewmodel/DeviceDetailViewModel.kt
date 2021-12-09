package com.aplication.techforest.viewmodel

import androidx.lifecycle.ViewModel
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.model.Device.ValveDeviceResponse
import com.aplication.techforest.repository.PlantRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceDetailViewModel @Inject constructor(
    private val repository: PlantRepository
) : ViewModel() {

    suspend fun getDeviceData(deviceId: Int) : Resource<DeviceResponse>{
        return repository.getDevice(deviceId)
    }

    suspend fun getDeviceOptions(deviceId: Int) : Resource<OptionDeviceResponse>{
        return repository.getOptionsDevice(deviceId)
    }

    suspend fun getDeviceValve(deviceId: Int) : Resource<ValveDeviceResponse>{
        return repository.getValveDevice(deviceId)
    }
}