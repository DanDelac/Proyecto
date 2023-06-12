package com.example.proyecto.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.model.QuoteModelUnidadSesion
import com.example.proyecto.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {

    val quoteModel = MutableLiveData<List<QuoteModelUnidadSesion>?>()

    var getQuotesUseCase = GetQuotesUseCase()

    fun onCreate(codigo:String){
        viewModelScope.launch {
            val result = getQuotesUseCase(codigo)
            val Aux =result
            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result)
            }
        }
    }
}