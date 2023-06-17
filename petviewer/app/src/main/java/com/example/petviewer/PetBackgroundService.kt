package com.example.petviewer

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.petviewer.network.PetInfo
import com.example.petviewer.network.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.system.measureTimeMillis

class PetBackgroundService: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}