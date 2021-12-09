package com.aplication.techforest.model.Device

data class OptionDeviceResponse(
    val diapositivos: Int,
    val humedad: String,
    val id: Int,
    var valor_maximo: Int,
    var valor_minimo: Int
)