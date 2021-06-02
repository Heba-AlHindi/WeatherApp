package com.example.weatherapp.network.datasources

import com.example.weatherapp.Constants
import com.example.weatherapp.network.NetworkClient
import com.example.weatherapp.network.models.CityForecastResponse
import com.example.weatherapp.network.services.ForecastApi
import io.reactivex.rxjava3.core.Single

class CityForecastRemoteDataSource {
    private val forecastApi = NetworkClient.client<ForecastApi>()
    fun callService(lat: Double, lon: Double): Single<CityForecastResponse> {
        return forecastApi.getDetailsForecastForOneCity(
            lat, lon,
            Constants.Forecast.EXCLUDE,
            Constants.Forecast.UNITS,
            Constants.Network.API_KEY_VALUE
        )
    }
}