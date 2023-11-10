package com.example.proyecto.core

import com.example.proyecto.domain.Util.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Util.RUTA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getRetrofit_API(): Retrofit {
        val baseUrl = Util.RUTA_API
        Log.d("RetrofitHelper", "Base URL: $baseUrl") // Agrega esta línea para imprimir la URL en el log

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}