package com.example.petviewer

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.petviewer.network.PetInfo

fun createDrawable(pet : PetInfo, context : Context){
    val loader = ImageLoader(context)
    val req = ImageRequest.Builder(context)
        .data(pet.petPhotoUrl)
        .target { result ->
            val bitmap = (result as BitmapDrawable)
            pet.photoDrawable = bitmap
        }
        .build()

    val disposable = loader.enqueue(req)
}
