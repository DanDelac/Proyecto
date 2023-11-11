package com.example.proyecto.data.model

data class ResPrediccion(
    val text:String,
    val mensaje:String
)
fun ResPrediccion.esVacio(): Boolean {
    return text.isEmpty() && mensaje.isEmpty()
}
