package com.aplication.techforest.repository

import com.aplication.techforest.model.plantModels.PlantsResponse
import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.network.plants.PlantsApiInterface
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import java.io.File
import javax.inject.Inject

@ActivityScoped
class PlantsRepository @Inject constructor(
    private val apiInterface: PlantsApiInterface
) {
    val APIKEY = "2b10DcZv6nwbAe17sLj2GxsAZ"

    suspend fun postPlant(
        images: File
    ): Response<PlantsResponse> {
        return apiInterface.getPlants(apikey = APIKEY, images = images)
    }

}