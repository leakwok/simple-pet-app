package com.example.petviewer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.petviewer.network.PetInfo
import com.example.petviewer.overview.OverviewList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
    }

    fun viewPetList(view: View) {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

}
