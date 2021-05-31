package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CitiesForecastResponse(
    @Json(name = "cnt")
    val citiesNo: Int? = null,

    @Json(name = "list")
    val citiesCurrentForecast: List<CurrentForecast?>? = null,
) : Parcelable
