package com.example.petviewer.overview

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.petviewer.databinding.PetcardBinding
import com.example.petviewer.databinding.PetlistBinding
import com.example.petviewer.network.PetInfo
import kotlinx.coroutines.launch


class OverviewFragment: Fragment() {
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PetcardBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        lifecycleScope.launch {

        }

        return binding.root

        // get a reference to the already created main layout


        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    private suspend fun convertToDrawable(pet: PetInfo): BitmapDrawable?{
        var bitmap: BitmapDrawable? = null
        val loader = ImageLoader(requireContext())
        val req = ImageRequest.Builder(requireContext())
            .data(pet.petPhotoUrl) // demo link
//            .target { result ->
//                bitmap = (result as BitmapDrawable)
//
//            }
            .build()

        val result = (loader.execute(req) as SuccessResult).drawable
        val disposable = loader.enqueue(req)
        return result as BitmapDrawable

    }
}