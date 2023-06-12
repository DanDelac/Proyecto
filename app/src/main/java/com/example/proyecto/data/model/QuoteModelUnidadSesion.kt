package com.example.proyecto.data.model

import com.example.proyecto.data.model.ModelSession

data class QuoteModelUnidadSesion(
    val idUseSes: Int,
    val idUser: Int,
    val oSes: ModelSession,
    var sesPorc: Int,
)
