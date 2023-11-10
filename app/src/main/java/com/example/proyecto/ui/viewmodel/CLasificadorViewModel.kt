package com.example.proyecto.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.model.Model_Base64
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import com.example.proyecto.domain.GetPrediccion
import com.example.proyecto.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

class CLasificadorViewModel: ViewModel() {
    val quoteModel = MutableLiveData<String?>()

    var getPrediccion = GetPrediccion()

    fun onCreate(modelBase64: Model_Base64){
        viewModelScope.launch {
            val result = getPrediccion(modelBase64)
            val Aux =result
            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result)
            }
        }
    }
}