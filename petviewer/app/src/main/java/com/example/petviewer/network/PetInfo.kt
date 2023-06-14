package com.example.petviewer.network

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.squareup.moshi.Json

data class PetInfo(
    @Json(name = "title")val petName: String,
    @Json(name = "description")val petDescription: String,
    @Json(name = "url")val petPhotoUrl: String,
    var photoDrawable: BitmapDrawable
)
