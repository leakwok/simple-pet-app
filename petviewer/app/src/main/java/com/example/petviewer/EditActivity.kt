package com.example.petviewer

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petviewer.network.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.DateFormat.getDateInstance
import java.util.Objects


class EditActivity: AppCompatActivity() {
    lateinit var url: String
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = intent.extras?.getString("urlString").toString()
        bitmap = intent.extras?.get("bitmap") as Bitmap

        setContentView(R.layout.petedit)

        val imageView = findViewById<ImageView>(R.id.editing_picture)
        imageView.setImageBitmap(bitmap)


    }

    private fun findSavePath(): File? {
        val path = File(
            Environment.getExternalStorageDirectory().toString()
                    + "/Android/data"
                    + applicationContext.packageName
                    + "/Files"
        )

        if (!path.exists()) {
            if(!path.mkdirs()){
                return null
            }

        }

        val imageFileName = "savedPet_" + getDateInstance() + ".jpg"
        return File(path.path + File.separator + imageFileName)

    }

    fun saveImage(view: View) {

        val imageFileName = "savedPet_" + getDateInstance() + ".jpg"
        val fos: OutputStream
        var saveFile: File? = null

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val resolver = contentResolver
            val contentValues = ContentValues().apply{
                put(MediaStore.MediaColumns.DISPLAY_NAME, imageFileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val itemUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = itemUri?.let { resolver.openOutputStream(it) }!!

            itemUri.let { uri ->
                val projection = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = contentResolver.query(uri, projection, null, null, null)
                cursor?.use {
                    val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    it.moveToFirst()
                    val filePath = it.getString(columnIndex)
                    saveFile = File(filePath)
                }

            }

        } else{
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val imageFile = File(imagesDir, imageFileName)
            fos = FileOutputStream(imageFile)
            saveFile = saveFile ?: findSavePath()
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        Objects.requireNonNull(fos).close()

        //upload to server
        CoroutineScope(Dispatchers.Main).launch{
            val uploadUrl = apiService.getUploadUrl()
            println(uploadUrl.url)

            uploadUrl.url.replace("https://eulerity-hackathon.appspot.com/", "")

            val requestFile = saveFile?.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imagePart =
                requestFile?.let { MultipartBody.Part.createFormData("image", saveFile?.name, it) }
            val appid = "leakwok".toRequestBody("text/plain".toMediaTypeOrNull())
            val original = url.toRequestBody("text/plain".toMediaTypeOrNull())

            if (imagePart != null) {
                apiService.uploadImage(uploadUrl.url, appid, original, imagePart)
            }
        }

        Toast.makeText(this@EditActivity, "Image saved to gallery", Toast.LENGTH_SHORT).show()


    }
}