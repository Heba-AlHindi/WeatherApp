package com.example.weatherapp.ui.location

import androidx.lifecycle.ViewModel
import com.example.weatherapp.ui.base.BaseViewModelFactory

class LocationViewModelFactory : BaseViewModelFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}