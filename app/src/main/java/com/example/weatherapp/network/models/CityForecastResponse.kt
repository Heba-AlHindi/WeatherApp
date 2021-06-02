package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityForecastResponse(
    @SerializedName("lat")
    val lat: Float,

    @SerializedName("lon")
    val lon: Float,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("hourly")
    val hourly: List<HourlyForecast>,

    @SerializedName("daily")
    val daily: List<DailyForecast>,
) : Parcelable

