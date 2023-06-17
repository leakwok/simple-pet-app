//package com.example.petviewer.overview
//
//import android.graphics.drawable.BitmapDrawable
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.LinearLayout
//import androidx.core.content.ContentProviderCompat
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import coil.ImageLoader
//import coil.request.ImageRequest
//import coil.request.SuccessResult
//import com.example.petviewer.ListActivity
//import com.example.petviewer.R
//import com.example.petviewer.databinding.PetcardBinding
//import com.example.petviewer.databinding.PetlistBinding
//import com.example.petviewer.network.PetInfo
//import kotlinx.coroutines.launch
//
//
//class OverviewFragment: Fragment() {
//    private val viewModel: OverviewViewModel by viewModels()
//    private var _binding: PetlistBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        val rootView: View = layoutInflater.inflate(R.layout.petlist, null)
////        val list: LinearLayout = rootView.findViewById<LinearLayout>(R.id.parent_linear_layout)
////        val layInflator: LayoutInflater = LayoutInflater.from(view?.context ?: context)
////        if(viewModel.pets.value != null){
////            for(pet: PetInfo in viewModel.pets.value!!){
////                val view: View = layInflator.inflate(R.layout.petcard, list, false)
////                list.addView(view)
////            }
////        }
//
////
////        val item: View = layoutInflater.inflate(R.layout.petcard, null)
//        // Inflate you custom xml to view
//        // Ex: res/layout/item_data.xml
////
////        if(viewModel.pets.value != null){
////            for(pet : PetInfo in viewModel.pets.value!!){
////                list.addView(item)
////            }
////        }
//
//
//        val innerBind = PetlistBinding.inflate(inflater)
//
//        //val inflate = getLayoutInflater().inflate(inflater)
//
//        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
//        innerBind.lifecycleOwner = this
//
//        // Giving the binding access to the OverviewViewModel
//        //innerBind.viewModel = viewModel
//
//        //binding.parentLinearLayout. = AdapterLinearLayout()
//
//        return innerBind.root
//
//        // get a reference to the already created main layout
//
//
//        //return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    private suspend fun convertToDrawable(pet: PetInfo): BitmapDrawable?{
//        var bitmap: BitmapDrawable? = null
//        val loader = ImageLoader(requireContext())
//        val req = ImageRequest.Builder(requireContext())
//            .data(pet.url) // demo link
////            .target { result ->
////                bitmap = (result as BitmapDrawable)
////
////            }
//            .build()
//
//        val result = (loader.execute(req) as SuccessResult).drawable
//        val disposable = loader.enqueue(req)
//        return result as BitmapDrawable
//
//    }
//
//    private fun addNewView(pet : PetInfo) {
//        // this method inflates the single item layout
//        // inside the parent linear layout
//        val inflater = LayoutInflater.from(context).inflate(R.layout.petcard, null)
//        binding.parentLinearLayout.addView(inflater, binding.parentLinearLayout.childCount)
//    }
//}