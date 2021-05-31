package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CurrentForecast(
    @SerializedName("coord")
    val coordination: Coordination? = null,

    @SerializedName("sys")
    val cityInfo: CityInfo? = null,

    @SerializedName("weather")
    val weather: List<Weather?>? = null,

    @SerializedName("main")
    val mainForecast: MainForecast? = null,

    @SerializedName("wind")
    val wind: Wind? = null,

    @SerializedName("clouds")
    val clouds: Clouds? = null,

    @SerializedName("dt")
    val currentTime: Int? = null,

    @SerializedName("visibility")
    val visibility: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,
) : Parcelable





