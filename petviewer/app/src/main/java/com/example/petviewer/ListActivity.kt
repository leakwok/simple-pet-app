package com.example.petviewer

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petviewer.databinding.PetlistBinding
import com.example.petviewer.network.PetInfo
import com.example.petviewer.network.apiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.URL
import kotlin.system.measureTimeMillis


class ListActivity: AppCompatActivity() {
    // initiate viewBinding
//    private var _binding: ListActivity? = null
//    private val binding get() = _binding!!
    private val containerLayout: LinearLayout
        get() = findViewById(R.id.parent_linear_layout)
    private val inflater: LayoutInflater
        get() = LayoutInflater.from(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("pet screen")
        setContentView(R.layout.petlist)
        CoroutineScope(Dispatchers.Main).launch {
            var petList: List<PetInfo>
            val petListPhotoMap = mutableMapOf<PetInfo, Bitmap>()
            val petViews = mutableListOf<View>()

            //load in all content
            val result = withContext(Dispatchers.IO){
                petList = apiService.getInfo()

                println(petList.size)

                for (pet: PetInfo in petList) {
                    val inflatedLayout: View =
                        inflater.inflate(
                            R.layout.petcard,
                            containerLayout,
                            false
                        )
                    val nameText: TextView = inflatedLayout.findViewById(R.id.name)
                    val descriptionText: TextView = inflatedLayout.findViewById(R.id.description)
                    val photoView: ImageView = inflatedLayout.findViewById(R.id.petPic)


                    println(pet.title)

                    val picUrl = URL(pet.url)
                    val bitmap: Bitmap =
                        BitmapFactory.decodeStream(picUrl.openConnection().getInputStream())
                    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 75, 75, false)
                    petListPhotoMap[pet] = resizedBitmap

                    nameText.text = pet.title

                    descriptionText.text = pet.description

                    photoView.setImageBitmap(petListPhotoMap[pet])

                    petViews.add(inflatedLayout)
                }
            }

            //add content to view
            for (view: View in petViews) {
                containerLayout.addView(view)
            }

        }




    }


}