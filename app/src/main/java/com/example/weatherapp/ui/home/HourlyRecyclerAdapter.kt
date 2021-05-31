package com.example.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.databinding.ItemHourlyBinding
import com.example.weatherapp.network.models.HourlyForecast
import com.example.weatherapp.ui.base.BaseRecyclerAdapter


//class HourlyRecyclerAdapter(private val hourlyForecasts: List<HourlyForecast>) :
//    BaseRecyclerAdapter() {
//
//    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
//        return ItemHourlyBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return hourlyForecasts.size
//    }
//
//    override fun bind(binding: ViewBinding, position: Int) {
//    }
//}