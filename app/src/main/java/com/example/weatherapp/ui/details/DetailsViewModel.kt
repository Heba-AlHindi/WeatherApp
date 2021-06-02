package com.example.weatherapp.ui.details

import androidx.lifecycle.LiveData
import com.example.weatherapp.database.models.CityForecastEntity
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.repositories.DetailsRepository
import com.example.weatherapp.ui.base.BaseViewModel

class DetailsViewModel : BaseViewModel() {
    private val detailsRepository: DetailsRepository = DetailsRepository()

    fun getCityDetailsForecast(lat: Double, lon: Double): LiveData<Resource<CityForecastEntity>> {
        return detailsRepository.getCityDetailsForecast("CITY_FORECAST", lat, lon)
    }

    override fun onCleared() {
        super.onCleared()
    }
}