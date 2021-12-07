package com.aplication.techforest.model.Device

data class DeviceResponse(
    val estado: String,
    val fecha_adquisicion: String,
    val id: Int,
    val imagen: String,
    val nombre: String,
    val usuarios: Int
)