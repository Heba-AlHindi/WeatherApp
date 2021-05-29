package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "lat")
    val lat: Float,

    @Json(name = "lon")
    val lon: Float,

    @Json(name = "timezone")
    val timezone: String,

    @Json(name = "current")
    val current: CurrentForecast,

    @Json(name = "hourly")
    val hourly: List<HourlyForecast>,

    @Json(name = "daily")
    val dailyForecast: List<DailyForecast>,
)

