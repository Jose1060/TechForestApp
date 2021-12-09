package com.aplication.techforest.network.plants

import com.aplication.techforest.model.plantModels.PlantsResponse
import retrofit2.Response
import retrofit2.http.*
import java.io.File
import javax.inject.Singleton

@Singleton
interface PlantsApiInterface {

    @FormUrlEncoded
    @POST("all/")
    suspend fun getPlants(@Query("api-key") apikey : String, @Field("images") images : File) : Response<PlantsResponse>

}