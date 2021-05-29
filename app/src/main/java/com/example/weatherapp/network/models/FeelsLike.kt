package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelsLike(
    @Json(name = "day")
    val dayTemp: Float = 0F,

    @Json(name = "night")
    val dayNight: Float = 0F,

    @Json(name = "eve")
    val dayEve: Float = 0F,

    @Json(name = "morn")
    val dayMorn: Float = 0F,
)
