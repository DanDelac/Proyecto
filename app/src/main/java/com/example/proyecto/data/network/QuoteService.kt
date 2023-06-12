package com.example.proyecto.data.network

import com.example.proyecto.core.RetrofitHelper
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(codigo:String):List<QuoteModelUnidadSesion> {
        val COD=codigo
        return withContext(Dispatchers.IO){
            val response = retrofit.create(Api::class.java).getAllQuotes("listarSesion.php?Cod="+codigo)
            response.body() ?: emptyList()
        }
    }
}