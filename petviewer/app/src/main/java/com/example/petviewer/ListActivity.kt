package com.example.petviewer

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import com.example.petviewer.network.PetInfo
import com.example.petviewer.overview.OverviewViewModel


class ListActivity: AppCompatActivity() {
    // initiate viewBinding
//    private var _binding: ListActivity? = null
//    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listfragment)

        // get a reference to the already created main layout
//        val listParent : LinearLayout = findViewById(R.id.petlist)
//        val inflater: LayoutInflater = layoutInflater
//        val cardChild: View = inflater.inflate(R.layout.petcard, listParent, false)
//
//        // make changes to our custom layout and its subviews
//        cardChild.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_main))
//
//        val card: Button = cardChild.findViewById(R.id.buttonPet) as Button
//
////        card.setCompoundDrawables(drawable.value, null, null, null)
////        String.format(resources.getString(R.string.bodytext), name.value, description.value )
//            //card.setText("hello")
//        String.format(resources.getString(R.string.bodytext), "lea", "jiapeppenfndan jfhfdhdhadjklsdau")
//            card.setCompoundDrawables(resources.getDrawable(R.drawable.ic_launcher_background), null, null, null)
//
//        // add our custom layout to the main layout
//        listParent.addView(cardChild)

    }

//    private val viewModel: OverviewViewModel by viewModels()
//    private val pet: LiveData<PetInfo> = Transformations.map(viewModel.photos) {
//        it[it.size -1]
//    }
//
//    private val name: LiveData<String> = Transformations.map(pet){
//        it.petName
//    }
//
//    private val description: LiveData<String> = Transformations.map(pet){
//        it.petDescription
//    }
//
//    private val drawable: LiveData<BitmapDrawable> = Transformations.map(pet){
//        it.photoDrawable
//    }

    // This function is called after
    // user clicks on addButton
//    private fun addInfoCard() {
//        // this method inflates the single item layout
//        // inside the parent linear layout
//        val inflater = LayoutInflater.from(this).inflate(R.layout.petcard, null)
//        binding.parentLinearLayout.addView(inflater, binding.parentLinearLayout.childCount)
//
//    }


}