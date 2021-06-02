package com.example.weatherapp.network.services

import com.example.weatherapp.network.models.CitiesForecastResponse
import com.example.weatherapp.network.models.CityForecastResponse
import io.reactivex.rxjava3.core.Single


import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  ForecastService Calls
 */
interface ForecastApi {

    // get current forecast for group of cities
    @GET("group")
    fun getCurrentForAllCities(
        @Query("id")
        exclude: String,
        @Query("units")
        units: String,
        @Query(value = "appid")
        appId: String
    ): Single<CitiesForecastResponse>

    // get details forecast for one city
    @GET("onecall")
    fun getDetailsForecastForOneCity(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("exclude")
        exclude: String,
        @Query("units")
        units: String,
        @Query(value = "appid")
        appId: String
    ): Single<CityForecastResponse>
}