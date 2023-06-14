package com.example.petviewer.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import com.example.petviewer.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class OverviewList: Fragment() {
    private val viewModel: OverviewViewModel by viewModels()

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View?{
//        //setContentView(R.layout.petlist)
//
//
//    }
}