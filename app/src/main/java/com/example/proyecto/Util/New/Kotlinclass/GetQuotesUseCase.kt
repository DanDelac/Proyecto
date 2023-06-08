package com.example.proyecto.Util.New.Kotlinclass

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(codigo:String):List<QuoteModelUnidadSesion>{
        return repository.getAllQuotes(codigo)
    }
}