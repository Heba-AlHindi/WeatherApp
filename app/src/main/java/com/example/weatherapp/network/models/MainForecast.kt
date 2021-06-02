package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainForecast(
    @SerializedName("temp")
    val temp: Double?,

    @SerializedName("temp_min")
    var temp_min: Double?,

    @SerializedName("temp_max")
    var temp_max: Double?,

    @SerializedName("feels_like")
    var feels_like: Double?,

    @SerializedName("humidity")
    val humidity: Int?,

    @SerializedName("pressure")
    val pressure: Double?,
) : Parcelable


