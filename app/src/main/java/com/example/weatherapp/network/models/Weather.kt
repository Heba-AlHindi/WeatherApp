package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "")
    val main: String = "",

    @Json(name = "")
    val description: String = "",

    @Json(name = "icon")
    val icon: String?,
)