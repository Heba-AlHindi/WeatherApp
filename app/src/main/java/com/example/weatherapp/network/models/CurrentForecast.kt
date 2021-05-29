package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentForecast(
    @Json(name = "")
    val time: Long = 0L,

    @Json(name = "")
    val temp: Float = 0F,

    @Json(name = "")
    val feelsLike: Float = 0F,

    @Json(name = "")
    val pressure: Int = 0,

    @Json(name = "")
    val humidity: Int = 0,

    @Json(name = "")
    val windSpeed: Int = 0,

    @Json(name = "")
    val visibilityDegree: Int = 0,

    @Json(name = "weather")
    val weather: List<Weather>
)






