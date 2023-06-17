package com.example.petviewer.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val PET_URL = "https://eulerity-hackathon.appspot.com/"

//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(PET_URL)
    .build()

interface PetInfoApiGatherer {
    @GET("pets")
    suspend fun getInfo(): List<PetInfo>
}

val apiService = retrofit.create(PetInfoApiGatherer::class.java)

//object PetApi {
//    val retrofitService: PetInfoApiGatherer by lazy { retrofit.create(PetInfoApiGatherer::class.java) }
//}