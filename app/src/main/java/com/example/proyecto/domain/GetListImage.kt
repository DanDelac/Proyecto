package com.example.proyecto.domain

import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.proyecto.R

class GetListImage {

    fun getList1():List<SlideModel>{
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.presentation, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.presentation2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.presentation3, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.presentation4, ScaleTypes.FIT))

        return imageList
    }
    fun getList2(): IntArray {
        val imageArray = intArrayOf(
            R.drawable.presentation,
            R.drawable.presentation2,
            R.drawable.presentation3,
            R.drawable.presentation4
        ) // Replace these with your images
        return imageArray
    }
}