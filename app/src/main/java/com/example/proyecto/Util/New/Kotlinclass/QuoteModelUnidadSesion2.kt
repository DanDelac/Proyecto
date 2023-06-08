package com.example.proyecto.Util.New.Kotlinclass

import com.google.gson.annotations.SerializedName

data class QuoteModelUnidadSesion2(
    val idUnit: Int,
    val uniDesc: String,
    @SerializedName("idUseSes") val idUseUni: Int,
    @SerializedName("sesDesc") val sesD: Int
)
