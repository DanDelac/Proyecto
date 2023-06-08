package com.example.proyecto.Util.New.Kotlinclass

class QuoteRepository {
    private val api = QuoteService()

    suspend fun getAllQuotes(codigo:String):List<QuoteModelUnidadSesion>{
        val response:List<QuoteModelUnidadSesion> = api.getQuotes(codigo)
        QuoteProvider.quotes = response
        return response
    }
}