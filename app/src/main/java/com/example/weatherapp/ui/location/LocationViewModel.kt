package com.example.weatherapp.ui.location

import androidx.lifecycle.LiveData
import com.example.weatherapp.database.models.cityCurrentForecast.CitiesForecastEntity
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.repositories.LocationRepository
import com.example.weatherapp.ui.base.BaseViewModel
import io.realm.Realm

class LocationViewModel : BaseViewModel() {
    //    val realm: Realm by lazy {
//        Realm.getDefaultInstance()
//    }
    private val locationRepo: LocationRepository = LocationRepository()

    fun getCitiesForecast(): LiveData<Resource<CitiesForecastEntity>> {
        return locationRepo.getCitiesForecast("CITY_FORECAST")
    }

    override fun onCleared() {
        super.onCleared()
    }

}