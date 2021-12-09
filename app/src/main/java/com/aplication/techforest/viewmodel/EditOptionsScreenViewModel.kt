package com.aplication.techforest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EditOptionsScreenViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    var myResponse: MutableLiveData<Response<OptionDeviceResponse>> = MutableLiveData()

    suspend fun getOptions(optionsId: Int): Resource<OptionDeviceResponse> {
        return repository.getOptionsDevice(optionsId)
    }

    /*
        suspend fun updateOptions(optionsId : Int, optionDeviceResponse : OptionDeviceResponse) : Resource<OptionDeviceResponse>{
            return repository.updateOptionsDevice(optionsId, optionDeviceResponse)
        }
    */
    fun updateOptions(optionsId: Int, OptionDeviceResponse: OptionDeviceResponse) {
        viewModelScope.launch {
            val response = repository.updateOptionsDevice(optionsId, OptionDeviceResponse)
            myResponse.value = response
        }
    }

}