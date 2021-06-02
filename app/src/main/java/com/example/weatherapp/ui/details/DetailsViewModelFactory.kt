package com.example.weatherapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.weatherapp.ui.base.BaseViewModelFactory

class DetailsViewModelFactory(private val lat: Double, private val lng: Double) :
    BaseViewModelFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) return DetailsViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}