package com.example.petviewer.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petviewer.network.PetApi
import com.example.petviewer.network.PetInfo
import com.example.petviewer.network.PetInfoApiGatherer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

enum class PetInfoStatus{ LOADING, ERROR, DONE}

class OverviewViewModel : ViewModel(){

    private var _status = MutableLiveData<String>()//MutableLiveData<PetInfoStatus>()
    val status: LiveData<String> = _status

    //val status: LiveData<PetInfoStatus> = _status

    private val _photos = MutableLiveData<PetInfo>()
    val photos: LiveData<PetInfo> = _photos

//    private val _photos = MutableLiveData<PetInfo>()
//    val photos: LiveData<PetInfo> = _photos

    init {
        getPetInfo()
    }

    private fun getPetInfo() {
        viewModelScope.launch {
            try{
                //val listResult = PetApi.retrofitService.getInfos()
                _photos.value = PetApi.retrofitService.getInfos()[0]
                _status.value = "   First pet image URL : ${_photos.value!!.petPhotoUrl}"
            } catch (e: Exception){
                _status.value = "fail ${e.message}"
            }

            println(_status.value)
            //_status.value = PetInfoStatus.LOADING
//            try {
//                _photos.value = PetApi.retrofitService.getInfos()
//                _status = "   First Mars image URL : ${_photos.value!!.petPhotoUrl}"
//
////                _photos.value = PetApi.retrofitService.getInfos()
////                _status.value = PetInfoStatus.DONE
//            } catch (e: Exception) {
//                //_status.value = PetInfoStatus.ERROR
//                //_photos.value = listOf()
//            }
        }
    }
}