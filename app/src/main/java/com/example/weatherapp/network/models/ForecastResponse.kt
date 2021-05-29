package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "lat")
    val lat: Double = 0.0,

    @Json(name = "lon")
    val lon: Double = 0.0,

    @Json(name = "timezone")
    val timezone: String = "",

    @Json(name = "current")
    val current: CurrentForecast,

    @Json(name = "hourly")
    val hourly: List<HourlyForecast>,

    @Json(name = "daily")
    val dailyForecast: List<DailyForecast>,
)

