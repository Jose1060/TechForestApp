package com.aplication.techforest.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.R
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Feature
import com.aplication.techforest.model.TimeResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.repository.TimeRepository
import com.aplication.techforest.ui.theme.BlueViolet1
import com.aplication.techforest.ui.theme.BlueViolet2
import com.aplication.techforest.ui.theme.BlueViolet3
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryDev: DeviceRepository,
    private val repositoryTime: TimeRepository
) : ViewModel() {

    var deviceList = mutableStateOf<List<DeviceResponse>>(listOf())
    var timeList = mutableStateOf<List<TimeResponse>>(listOf())
    var featureList = mutableListOf<Feature>()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadTimeHome()
    }

    suspend fun getDevicesHome(
        userId: Int
    ): Resource<DeviceResponse> {

        return repositoryDev.getDeviceUser(userId)
    }

    fun loadTimeHome() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repositoryTime.getTime("Arequipa")) {
                is Resource.Success -> {
                    val timeEntries = result.data!!

                    loadError.value = ""
                    isLoading.value = false
                    timeList.value += timeEntries
                    Log.d("Error_Time", "$timeEntries")
                }
                is Resource.Error -> {
                    Log.d("Error_Time", "${result.message}")
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> TODO()
            }
        }
    }
}