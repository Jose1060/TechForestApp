package com.aplication.techforest.network

import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.model.Device.ValveDeviceResponse
import com.aplication.techforest.model.User.UserResponse
import okhttp3.RequestBody
import retrofit2.http.*
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

    @GET("usuario/{id}")
    suspend fun getUser(@Path("id") id: Int) : UserResponse



    //PUT AREA
    @FormUrlEncoded
    @PUT("opciones/dispositivo/{id}")
    suspend fun updateOptions(@Path("id") id: Int, @Body optionDeviceResponse: OptionDeviceResponse) : OptionDeviceResponse

    @PUT("valvula/dispositivo/{id}")
    suspend fun updateValve(@Path("id") id : Int, @Body valveDeviceResponse: ValveDeviceResponse) : ValveDeviceResponse

    @PUT("dispositivos/{id}")
    suspend fun updateDevice(@Path("id") id: Int, @Body deviceResponse: DeviceResponse) : DeviceResponse

    @PUT("usuario/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body userResponse: UserResponse) : UserResponse

}







