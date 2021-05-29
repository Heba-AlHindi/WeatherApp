package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DailyForecast(
    @Json(name = "")
    val time: Long,

    @Json(name = "")
    val temp: Temp,

    @Json(name = "")
    val feelsLike: FeelsLike,

    @Json(name = "")
    val pressure: Int,

    @Json(name = "")
    val humidity: Int,

    @Json(name = "")
    val windSpeed: Int,

    @Json(name = "weather")
    val weather: List<Weather>
)
