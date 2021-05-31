package com.example.weatherapp.network.datasources

import android.util.Log
import com.example.weatherapp.Constants
import com.example.weatherapp.network.NetworkClient
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.example.weatherapp.network.services.ForecastApi
import io.reactivex.rxjava3.core.Single
import retrofit2.create


/**
 *  contains all webservice method calls for citiesForecast
 */
class CitiesForecastRemoteDataSource {

    fun callService(): Single<CitiesForecastResponse> {
        Log.e("Cities", "Inside RemoteDataSource")
        return NetworkClient.retrofit().create(ForecastApi::class.java).getCurrentForAllCities(
            Constants.Forecast.IDS,
            Constants.Forecast.UNITS,
            Constants.Network.API_KEY_VALUE
        )
    }
}