package com.aplication.techforest.model.Device

data class OptionDeviceResponse(
    val diapositivos: Int,
    val humedad: String,
    val id: Int,
    val valor_maximo: String,
    val valor_minimo: String
)