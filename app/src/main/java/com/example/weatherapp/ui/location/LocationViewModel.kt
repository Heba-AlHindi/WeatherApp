package com.example.weatherapp.ui.location

import android.provider.SyncStateContract
import androidx.lifecycle.LiveData
import com.example.weatherapp.Constants
import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.repositories.LocationRepository
import com.example.weatherapp.ui.base.BaseViewModel

class LocationViewModel : BaseViewModel() {
    private val locationRepo: LocationRepository = LocationRepository()

    fun getCitiesForecast(): LiveData<Resource<CitiesForecastEntity>> {
        return locationRepo.getCitiesForecast(Constants.Prefs.LOCATIONS_LAST_FETCHED)
    }

    override fun onCleared() {
        super.onCleared()
    }
}