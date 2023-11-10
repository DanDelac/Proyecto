package com.example.proyecto.data.network

import com.example.proyecto.core.RetrofitHelper
import com.example.proyecto.data.model.Model_Base64
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()
//    private val retrofit_api = RetrofitHelper.getRetrofit_API()

    suspend fun getQuotes(codigo:String):List<QuoteModelUnidadSesion> {
        val COD=codigo
        return withContext(Dispatchers.IO){
            val response = retrofit.create(Api::class.java).getAllQuotes("listarSesion.php?Cod="+codigo)
            response.body() ?: emptyList()
        }
    }
//    suspend fun getPrediccion(model_Base64: Model_Base64):String {
//        val COD=model_Base64
//        return withContext(Dispatchers.IO){
//            val response_api = retrofit_api.create(Api::class.java).getPrediccion(model_Base64)
//            response_api.body() ?: "error en la respuesta"
//        }
//    }
}