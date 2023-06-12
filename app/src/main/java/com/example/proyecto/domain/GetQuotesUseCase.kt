package com.example.proyecto.domain

import com.example.proyecto.data.QuoteRepository
import com.example.proyecto.data.model.QuoteModelUnidadSesion

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    suspend operator fun invoke(codigo:String):List<QuoteModelUnidadSesion>{
        return repository.getAllQuotes(codigo)
    }
}