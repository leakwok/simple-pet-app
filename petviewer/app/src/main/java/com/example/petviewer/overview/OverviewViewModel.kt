package com.example.petviewer.overview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petviewer.ListActivity
import com.example.petviewer.R
import com.example.petviewer.databinding.PetlistBinding
import com.example.petviewer.network.PetApi
import com.example.petviewer.network.PetInfo
import kotlinx.coroutines.launch

enum class PetInfoStatus{ LOADING, ERROR, DONE}

class OverviewViewModel : ViewModel(){

    private var _binding: PetlistBinding? = null
    private val binding get() = _binding!!

    private var _status = MutableLiveData<PetInfoStatus>()//MutableLiveData<PetInfoStatus>()
    val status: LiveData<PetInfoStatus> = _status

    //val status: LiveData<PetInfoStatus> = _status

    private var _pets = MutableLiveData<List<PetInfo>>()

    val pets: LiveData<List<PetInfo>> = _pets

//    private val _photos = MutableLiveData<PetInfo>()
//    val photos: LiveData<PetInfo> = _photos

    init {
        //getPetInfo()
        println("DONE ADDING PETS")
        //wait for function to finish
//        suspend {
//            for(pet: PetInfo in photos.value!!){
//                val view: View = ListActivity().inflate.inflate(R.layout.petcard, ListActivity().linLay, false)
//                ListActivity().linLay.addView(view)
//            }
//        }

    }

    private fun getPetInfo() : List<PetInfo> {
        viewModelScope.launch {
            _status.value = PetInfoStatus.LOADING
            try{
                //val listResult = PetApi.retrofitService.getInfos()
                _pets.value = PetApi.retrofitService.getInfos()
                _status.value = PetInfoStatus.DONE
            } catch (e: Exception){
                _status.value = PetInfoStatus.ERROR
                _pets.value = listOf()
            }

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

        if(_pets.value == null){
            println("WHAT HAPPENED")
        }

        return _pets.value ?: listOf()
        //println(_status.value)
    }

//    private fun addNewView(pet : PetInfo) {
//        // this method inflates the single item layout
//        // inside the parent linear layout
//        val layoutInflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val group: ViewGroup = LinearLayout
//        val inflater = LayoutInflater.from(layoutInflater.inflate(R.layout.listfragment, ViewGroup.LinearLayout).getContext()).inflate(R.layout.petcard, null)
//        binding.parentLinearLayout.addView(inflater, binding.parentLinearLayout.childCount)
//    }
}