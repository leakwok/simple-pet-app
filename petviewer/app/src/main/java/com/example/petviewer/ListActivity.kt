package com.example.petviewer

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SearchView
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

    private val petViews = mutableListOf<View>()
    private lateinit var petList: List<PetInfo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("pet screen")
        setContentView(R.layout.petlist)

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE

        //load in pets and display all
        CoroutineScope(Dispatchers.Main).launch {
            val petListPhotoMap = mutableMapOf<PetInfo, Bitmap>()


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
                    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false)
                    petListPhotoMap[pet] = resizedBitmap


                    nameText.text = pet.title

                    descriptionText.text = pet.description

                    photoView.setImageBitmap(petListPhotoMap[pet])

                    inflatedLayout.setOnClickListener {
                        onClick(it, pet.url, resizedBitmap)
                    }

                    petViews.add(inflatedLayout)
                    progressBar.progress++
                    Thread.sleep(100)
                }
            }

            //add content to view
            for (view: View in petViews) {
                containerLayout.addView(view)
            }

        }

        progressBar.visibility = View.GONE

        val searchView = findViewById<SearchView>(R.id.search_bar)

        if(Intent.ACTION_SEARCH == intent.action){
            containerLayout.removeAllViews()
            intent.getStringExtra(SearchManager.QUERY)?.also{
                    query -> filterPets(query)
            }
        }


    }

    private fun onClick(view: View, url: String, bitmap: Bitmap) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("urlString", url)
        intent.putExtra("bitmap", bitmap)

        startActivity(intent)
    }

    private fun filterPets(query: String) {
        //find names and/or descriptions matching words in string
        val searchWords = query.trim().split(" ")

        val searchResults = petList.filter { petInfo ->
            searchWords.any{word ->
                petInfo.title.contains(word, ignoreCase = true) ||
                        petInfo.description.contains(word, ignoreCase = true)
            }
        }.map { it.title }

        val correspondingViews = petViews.filter { view ->
            val viewName = view.findViewById<TextView>(R.id.name)
            searchResults.contains(viewName.text)
        }

        correspondingViews.forEach { view ->
            // Do something with the corresponding views
            containerLayout.addView(view)
        }


    }


}