package com.example.proyecto.data

import com.example.proyecto.data.model.Model_Base64
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import com.example.proyecto.domain.Util.New.Kotlinclass.QuoteProvider
import com.example.proyecto.data.network.QuoteService

class QuoteRepository {
    private val api = QuoteService()

    suspend fun getAllQuotes(codigo:String):List<QuoteModelUnidadSesion>{
        val response:List<QuoteModelUnidadSesion> = api.getQuotes(codigo)
        QuoteProvider.quotes = response
        return response
    }
//    suspend fun getPrediccion(model_Base64: Model_Base64):String {
//        val response:String = api.getPrediccion(model_Base64)
////        QuoteProvider.quotes = response
//        return response
//    }
}