package com.example.petviewer.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petviewer.network.PetApi
import com.example.petviewer.network.PetInfo
import com.example.petviewer.network.PetInfoApiGatherer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class PetInfoStatus{ LOADING, ERROR, DONE}

class OverviewViewModel : ViewModel(){

    private val _status = MutableLiveData<PetInfoStatus>()

    val status: LiveData<PetInfoStatus> = _status

    private val _photos = MutableLiveData<List<PetInfo>>()

    val photos: LiveData<List<PetInfo>> = _photos

    init {
        getPetInfo()
    }

    private fun getPetInfo() {

        viewModelScope.launch {
            _status.value = PetInfoStatus.LOADING
            try {
                _photos.value = PetApi.retrofitService.getInfo()
                _status.value = PetInfoStatus.DONE
            } catch (e: Exception) {
                _status.value = PetInfoStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}