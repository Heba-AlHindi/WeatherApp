package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelsLike(
    @Json(name = "day")
    val dayTemp: Float,

    @Json(name = "night")
    val dayNight: Float,

    @Json(name = "eve")
    val dayEve: Float,

    @Json(name = "morn")
    val dayMorn: Float,
)
