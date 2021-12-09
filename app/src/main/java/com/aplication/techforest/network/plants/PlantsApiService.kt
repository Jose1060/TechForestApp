package com.aplication.techforest.network.plants

import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.repository.PlantRepository
import com.aplication.techforest.repository.PlantsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class PlantsApiService {
    @Singleton
    @Provides
    fun providePlantsRepository(
        api: PlantsApiInterface
    ) = PlantsRepository(api)

    @Singleton
    @Provides
    fun providesDeviceApi(): ApiInterface {
        var okHttpClient : OkHttpClient? = null
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://my-api.plantnet.org/v2/identify/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }
}
