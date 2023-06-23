package com.example.proyecto.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denzcoskun.imageslider.models.SlideModel
import com.example.proyecto.domain.GetListImage

class PresentationViewModel: ViewModel() {

    val slideModel = MutableLiveData<List<SlideModel>?>()

    var getListImage = GetListImage()

    fun onCreate(){
        val result = getListImage
        val Aux =result
        if(!result.getList().isNullOrEmpty()){
            slideModel.postValue(result.getList())
        }
    }
}