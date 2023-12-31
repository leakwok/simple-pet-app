package com.example.petviewer.network

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContentProviderCompat.requireContext
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult


//@JsonClass(generateAdapter = true)
data class PetInfo(
    val title: String,
    val description: String,
    val url: String,
    val created: String
    //var photoDrawable: BitmapDrawable? = null
){
//    fun getName(): String {
//        return petName
//    }
//
//    fun getDescription(): String{
//        return petDescription
//    }
//
//    fun getPhotoUrl(): String{
//        return petPhotoUrl
//    }
//
//    fun getPhotoDrawable(): BitmapDrawable{
//        return photoDrawable
//    }



}
