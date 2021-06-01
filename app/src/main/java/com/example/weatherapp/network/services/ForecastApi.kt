package com.example.weatherapp.network.services

import com.example.weatherapp.network.models.CitiesForecastResponse
import com.example.weatherapp.network.models.ForecastResponse
import io.reactivex.rxjava3.core.Single


import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  ForecastService Calls
 */
interface ForecastApi {

    @GET("group")
    fun getCurrentForAllCities(
        @Query("id")
        exclude: String,
        @Query("units")
        units: String,
        @Query(value = "appid")
        appId: String
    ): Single<CitiesForecastResponse>

    @GET("")
    fun getForecastByGeoCoordination(
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
    ): Single<ForecastResponse>
}