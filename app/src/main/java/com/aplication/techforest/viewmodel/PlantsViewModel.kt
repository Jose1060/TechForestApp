package com.aplication.techforest.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.model.plantModels.PlantsResponse
import com.aplication.techforest.repository.PlantsRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class PlantsViewModel @Inject constructor(
    private val repository : PlantsRepository
) : ViewModel() {
    var myResponse : MutableLiveData<Response<PlantsResponse>> = MutableLiveData()

    fun getPlants(images : File){
        viewModelScope.launch{
            val response = repository.postPlant(images)
            myResponse.value = response
        }
    }
}