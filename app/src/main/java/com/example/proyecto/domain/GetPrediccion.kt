package com.example.proyecto.domain

import com.example.proyecto.data.QuoteRepository
import com.example.proyecto.data.model.Model_Base64
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import com.example.proyecto.data.model.ResPrediccion

class GetPrediccion {
    private val repository = QuoteRepository()

    suspend operator fun invoke(model_Base64: Model_Base64): ResPrediccion {
        return repository.getPrediccion(model_Base64)
    }
}