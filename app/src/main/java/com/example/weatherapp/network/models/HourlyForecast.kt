package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class HourlyForecast(
    @SerializedName("dt")
    val dt: Long,

    @SerializedName("temp")
    val temp: Float,

    @SerializedName("feels_like")
    val feels_like: Float,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("wind_speed")
    val wind_speed: Double,

    @SerializedName("wind_deg")
    val wind_deg: Int,

    @SerializedName("visibility")
    val visibility: Int,

    @SerializedName("weather")
    val weather: List<Weather>
) : Parcelable