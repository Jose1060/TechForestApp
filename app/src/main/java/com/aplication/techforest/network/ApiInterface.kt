package com.aplication.techforest.network

import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.model.Device.ValveDeviceResponse
import com.aplication.techforest.model.User.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    //GET AREA

    //@GET("diapositivos")
    //suspend fun getDeviceData() : List<DeviceResponse>

    //@GET("users/")
    //suspend fun getUser(@Query("usuario") usuario : String) : List<UserResponse>

    @GET("usuario/correo/{correo}")
    suspend fun getUserMail(@Path("correo") correo : String) : UserResponse

    @GET("opciones/dispositivo/{id}")
    suspend fun getOptionsDevice(@Path("id") id : Int) : OptionDeviceResponse

    @GET("valvula/dispositivo/{id}")
    suspend fun getValveDevice(@Path("id") id : Int) : ValveDeviceResponse

    @GET("dispositivos/usuario/{id}")
    suspend fun getDevicesUser(@Path("id") id : Int) : DeviceResponse

    @GET("dispositivos/{id}")
    suspend fun getDeviceData(@Path("id") id : Int) : DeviceResponse

}







