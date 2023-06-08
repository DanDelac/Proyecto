package com.example.proyecto.Util.New.Kotlinclass

import com.example.proyecto.Util.New.RetrofitHelper
import com.example.proyecto.Util.Util
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Util.RUTA)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}