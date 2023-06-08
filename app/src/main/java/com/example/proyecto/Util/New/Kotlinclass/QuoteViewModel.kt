package com.example.proyecto.Util.New.Kotlinclass

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuoteViewModel:ViewModel() {

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