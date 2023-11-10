package com.example.proyecto.data.network

import com.example.proyecto.data.model.Model_Base64
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun getAllQuotes(@Url url:String ): Response<List<QuoteModelUnidadSesion>>
    @POST("Img_base64")
    suspend fun getPrediccion(@Body model_base64:Model_Base64): Response<String>

//    @GET("prueba.php?Cod={id}")
//    suspend fun getAllQuotes(@Path("id") id:Int ): Response<List<QuoteModel>>
}