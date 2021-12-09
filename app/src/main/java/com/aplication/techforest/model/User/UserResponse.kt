package com.aplication.techforest.model.User

data class UserResponse(
    val apellidos: String,
    val contraseña: String,
    val correo: String,
    val id: Int,
    val imagen_perfil: String,
    val nombres: String
)