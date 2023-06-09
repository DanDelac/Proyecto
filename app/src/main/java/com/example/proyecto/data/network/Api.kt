package com.example.proyecto.data.network

import com.example.proyecto.data.model.QuoteModelUnidadSesion
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun getAllQuotes(@Url url:String ): Response<List<QuoteModelUnidadSesion>>

//    @GET("prueba.php?Cod={id}")
//    suspend fun getAllQuotes(@Path("id") id:Int ): Response<List<QuoteModel>>
}