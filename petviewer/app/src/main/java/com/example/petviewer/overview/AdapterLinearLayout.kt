//package com.example.petviewer.overview
//
//import android.annotation.TargetApi
//import android.content.Context
//import android.database.DataSetObserver
//import android.os.Build
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Adapter
//import android.widget.LinearLayout
//import androidx.annotation.NonNull
//import androidx.annotation.Nullable
//import androidx.databinding.adapters.LinearLayoutBindingAdapter
//import androidx.recyclerview.widget.AdapterListUpdateCallback
//import androidx.recyclerview.widget.AsyncDifferConfig
//import androidx.recyclerview.widget.AsyncListDiffer
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListUpdateCallback
//import com.example.petviewer.ListActivity
//import com.example.petviewer.R
//import com.example.petviewer.databinding.PetcardBinding
//import com.example.petviewer.network.PetInfo
//import kotlinx.coroutines.currentCoroutineContext
//import java.security.AccessController.getContext
//import kotlin.coroutines.coroutineContext
//
//
//public class AdapterLinearLayout: LinearLayoutBindingAdapter() {
//    val diffCallback : DiffUtil.ItemCallback<T> = TODO()
//    val mDiffer: AsyncListDiffer<T> = object : AsyncListDiffer<>(object : ListUpdateCallback(this),
//        object : AsyncDifferConfig.Builder<>(diffCallback).build())
//    private var adapter: Adapter? = null
//    private val dataSetObserver: DataSetObserver = object : DataSetObserver() {
//        override fun onChanged() {
//            super.onChanged()
//            reloadChildViews()
//        }
//    }
//
//    fun AdapterLinearLayout(context: Context?, attrs: AttributeSet?, defStyle: Int) {
//        super.LinearLayout(context, attrs, defStyle)
//        orientation = HORIZONTAL
//    }
//
//    fun AdapterLinearLayout(context: Context?, attrs: AttributeSet?) {
//        super(context, attrs)
//        orientation = HORIZONTAL
//    }
//
//    fun AdapterLinearLayout(context: Context?) {
//        super(context)
//        orientation = HORIZONTAL
//    }
//
//    fun submitList(list : List<T> ){
//        mDiffer.submitList(list)
//    }
//
//    fun setAdapter(adapter: Adapter?) {
//        if (this.adapter === adapter) return
//        this.adapter = adapter
//        adapter?.registerDataSetObserver(dataSetObserver)
//        reloadChildViews()
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        adapter?.unregisterDataSetObserver(dataSetObserver)
//    }
//
//    private fun reloadChildViews() {
//        removeAllViews()
//        if (adapter == null) return
//        val count: Int = adapter!!.getCount()
//        for (position in 0 until count) {
//            val v: View = adapter!!.getView(position, null, this)
//            addView(v)
//        }
//        requestLayout()
//    }
//
//    fun onCreateViewHolder(){
//        PetcardBinding.inflate(LayoutInflater.from(parent.context))
//    }
//
//    fun onBindViewHolder(){
//        val petItem = getInfo(position)
//        holder.bind(petItem)
//    }
//
//    fun bind(PetInfo: PetInfo, binding : PetcardBinding){
//        binding.pet = PetInfo
//        binding.executePendingBindings()
//    }
//}