package com.example.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.databinding.ItemDataBinding
import com.example.weatherapp.network.models.CurrentForecast
import com.example.weatherapp.ui.base.BaseRecyclerAdapter

//class CurrentRecyclerAdapter(private val forecastsData: CurrentForecast) :
//    BaseRecyclerAdapter() {
//
//    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
//        return ItemDataBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//    }
//
//    override fun bind(binding: ViewBinding, position: Int) {
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//}