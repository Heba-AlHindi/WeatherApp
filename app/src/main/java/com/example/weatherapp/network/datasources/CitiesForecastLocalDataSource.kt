package com.example.weatherapp.network.datasources

import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.network.models.CitiesForecastResponse

class CitiesForecastLocalDataSource {

    fun insertForecast(item: CitiesForecastResponse) {

    }

    fun loadForecast(): CitiesForecastEntity {
        return CitiesForecastEntity()
    }
}