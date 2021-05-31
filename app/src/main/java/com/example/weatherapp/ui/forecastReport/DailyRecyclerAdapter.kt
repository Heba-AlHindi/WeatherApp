package com.example.weatherapp.ui.forecastReport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.databinding.ItemDailyBinding
import com.example.weatherapp.network.models.DailyForecast
import com.example.weatherapp.ui.base.BaseRecyclerAdapter

//class DailyRecyclerAdapter(private val dailyForecasts: List<DailyForecast>) :
//    BaseRecyclerAdapter() {
//
//    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
//        return ItemDailyBinding.inflate(
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
//        return dailyForecasts.size
//    }
//}