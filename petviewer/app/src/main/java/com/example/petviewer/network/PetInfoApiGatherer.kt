package com.example.petviewer.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url

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

    @GET("upload")
    suspend fun getUploadUrl(): UploadObject

    @Multipart
    @POST
    suspend fun uploadImage(
        @Url endpoint: String,
        @Part("appid") name: RequestBody,
        @Part("original") originalUrl: RequestBody,
        @Part editedPetImage: MultipartBody.Part
        )
}

val apiService = retrofit.create(PetInfoApiGatherer::class.java)

//object PetApi {
//    val retrofitService: PetInfoApiGatherer by lazy { retrofit.create(PetInfoApiGatherer::class.java) }
//}