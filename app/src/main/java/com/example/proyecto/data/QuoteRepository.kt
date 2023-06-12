package com.example.proyecto.data

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
}