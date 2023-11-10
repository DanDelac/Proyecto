package com.example.proyecto.core

import com.example.proyecto.domain.Util.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Util.RUTA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getRetrofit_API(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Util.RUTA_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}