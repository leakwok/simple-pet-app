package com.example.petviewer

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.LinearLayoutBindingAdapter
import coil.load
import com.example.petviewer.network.PetInfo
import com.example.petviewer.overview.PetInfoStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("text")
fun bindText(textView: TextView, textInput: String?){
    textInput?.let{
        textView.text = textInput
    }
}

@BindingAdapter("app:listData")
fun bindLinearLayout(linearLayout: LinearLayout, data: List<PetInfo>?){
//    val adapter = linearLayout as LinearLayoutBindingAdapter
//    adapter.submitList(data)
    //linearLayout.adapter
    //linearLayout.listData = data
}

@BindingAdapter("petInfoStatus")
fun bindStatus(statusImageView: ImageView, status: PetInfoStatus) {
    when (status) {
        PetInfoStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PetInfoStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        PetInfoStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}