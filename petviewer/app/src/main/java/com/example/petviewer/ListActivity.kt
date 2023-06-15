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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.URL


class ListActivity: AppCompatActivity() {
    // initiate viewBinding
//    private var _binding: ListActivity? = null
//    private val binding get() = _binding!!
    val containerLayout: LinearLayout
        get() = findViewById(R.id.parent_linear_layout)
    val inflater: LayoutInflater
        get() = LayoutInflater.from(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //_binding = PetlistBinding.inflate(layoutInflater)
//        val model : OverviewViewModel by viewModels()
//        for(pet : PetInfo in model.photos.value!!){
//            addNewView(pet)
//        }

        // ViewGroup to add views


        setContentView(R.layout.petlist)

        //JsonTask().execute("")

        // Link item in list here
        val petTask: PetTask = PetTask()
        petTask.execute()

    }

    inner class PetTask : AsyncTask<Void, Void, String>() {

        lateinit var petList: List<PetInfo>

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): String {
            try {
                val url: URL = URL("https://eulerity-hackathon.appspot.com/pets")
                val reader: InputStreamReader = InputStreamReader(url.openStream())
                val gson: Gson = Gson()

                val petListType: Type = object : TypeToken<ArrayList<PetInfo>>() {}.type
                petList = gson.fromJson(reader, petListType)
                println(petList.size)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return "done"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            for (pet: PetInfo in petList) {
                val inflatedLayout: View =
                    inflater.inflate(R.layout.petcard, containerLayout, false)

                val nameText: TextView = inflatedLayout.findViewById(R.id.name)
                nameText.text = pet.petName

                val descriptionText: TextView = inflatedLayout.findViewById(R.id.description)
                descriptionText.text = pet.petDescription

                val photoView: ImageView = inflatedLayout.findViewById(R.id.petPic)
                if (pet.petPhotoUrl != null) {
                    val picUrl = URL(pet.petPhotoUrl)

                    val bitmap: Bitmap =
                        BitmapFactory.decodeStream(picUrl.openConnection().getInputStream())
                    photoView.setImageBitmap(bitmap)
                }

                containerLayout.addView(inflatedLayout)
            }
        }

    }
}