package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temp(
    @Json(name = "day")
    val dayTemp: Float,

    @Json(name = "min")
    val dayMin: Float,

    @Json(name = "max")
    val dayMax: Float,

    @Json(name = "night")
    val dayNight: Float,

    @Json(name = "eve")
    val dayEve: Float,

    @Json(name = "morn")
    val dayMorn: Float,
)