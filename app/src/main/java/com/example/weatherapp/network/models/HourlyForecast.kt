package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class HourlyForecast(
    @Json(name = "")
    val time: Long,

    @Json(name = "")
    val temp: Float,

    @Json(name = "")
    val feelsLike: Float,

    @Json(name = "")
    val pressure: Int,

    @Json(name = "")
    val humidity: Int,

    @Json(name = "")
    val windSpeed: Int,

    @Json(name = "")
    val visibilityDegree: Int,

    @Json(name = "weather")
    val weather: List<Weather>
)