package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CitiesForecastResponse(
    @SerializedName( "cnt")
    val citiesNo: Int? = null,

    @SerializedName("list")
    val citiesCurrentForecast: List<CurrentForecast?>? = null,
) : Parcelable
