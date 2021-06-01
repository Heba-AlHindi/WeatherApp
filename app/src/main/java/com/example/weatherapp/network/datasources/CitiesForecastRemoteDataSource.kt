package com.example.weatherapp.network.datasources

import com.example.weatherapp.Constants
import com.example.weatherapp.network.NetworkClient
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.example.weatherapp.network.services.ForecastApi
import io.reactivex.rxjava3.core.Single

/**
 *  contains all webservice method calls for citiesForecast
 */
class CitiesForecastRemoteDataSource {
    private val forecastApi = NetworkClient.client<ForecastApi>()
    fun callService(): Single<CitiesForecastResponse> {
        return forecastApi.getCurrentForAllCities(
            Constants.Forecast.IDS,
            Constants.Forecast.UNITS,
            Constants.Network.API_KEY_VALUE
        )
    }
}