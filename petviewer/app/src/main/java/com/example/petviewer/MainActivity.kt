package com.example.petviewer

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.petviewer.network.PetInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("intro screen")
//        val intent = Intent(this, PetBackgroundService::class.java)
//        startService(intent)

        setContentView(R.layout.layout)


    }
    fun viewPetList(view : View) {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }



    }


