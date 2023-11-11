package com.example.proyecto.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.model.Model_Base64
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import com.example.proyecto.data.model.ResPrediccion
import com.example.proyecto.data.model.esVacio
import com.example.proyecto.domain.GetPrediccion
import kotlinx.coroutines.launch

class CLasificadorViewModel: ViewModel() {
    val quoteModel = MutableLiveData<ResPrediccion?>()

    var getPrediccion = GetPrediccion()

    fun onCreate(modelBase64: Model_Base64){
        viewModelScope.launch {
            val result = getPrediccion(modelBase64)
            val Aux =result
            if(!result.esVacio()){
                quoteModel.postValue(result)
            }
        }
    }
}