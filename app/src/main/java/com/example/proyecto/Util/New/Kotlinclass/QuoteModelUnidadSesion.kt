package com.example.proyecto.Util.New.Kotlinclass

import com.google.gson.annotations.SerializedName

data class QuoteModelUnidadSesion(
    val idUseSes: Int,
    val idUser: Int,
    val oSes: ModelSession,
    var sesPorc: Int,
)
