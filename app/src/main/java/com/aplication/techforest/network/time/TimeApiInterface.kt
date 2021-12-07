package com.aplication.techforest.network.time

import com.aplication.techforest.model.TimeResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

val apiKey: String = "58bc116c41a039cb2a695df6df78c5cf"

@Singleton
interface TimeApiInterface {
    @GET("weather/")
    suspend fun getTime(@Query("q") city : String = "Arequipa", @Query("appid") apiKey : String = "58bc116c41a039cb2a695df6df78c5cf") : TimeResponse
}