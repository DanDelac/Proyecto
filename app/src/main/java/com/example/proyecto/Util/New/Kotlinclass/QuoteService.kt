package com.example.proyecto.Util.New.Kotlinclass

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