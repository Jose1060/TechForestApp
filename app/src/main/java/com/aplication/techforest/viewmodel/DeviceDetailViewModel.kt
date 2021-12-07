package com.aplication.techforest.viewmodel

import androidx.lifecycle.ViewModel
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.model.Device.ValveDeviceResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceDetailViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    suspend fun getDeviceData(deviceId: Int) : Resource<DeviceResponse>{
        return repository.getDevice(deviceId)
    }

    suspend fun getDeviceOptions(deviceId: Int) : Resource<OptionDeviceResponse>{
        return repository.getOptions(deviceId)
    }

    suspend fun getDeviceValvula(deviceId: Int) : Resource<ValveDeviceResponse>{
        return repository.getValve(deviceId)
    }
}