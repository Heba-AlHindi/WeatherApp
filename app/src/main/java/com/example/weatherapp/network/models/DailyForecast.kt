package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DailyForecast(
    @SerializedName("dt")
    val dt: Long,

    @SerializedName("temp")
    val temp: Temp,

    @SerializedName("feels_like")
    val feels_like: FeelsLike,

    @SerializedName( "pressure")
    val pressure: Int,

    @SerializedName( "humidity")
    val humidity: Int,

    @SerializedName( "wind_speed")
    val wind_speed: Double,

    @SerializedName("weather")
    val weather: List<Weather>
) : Parcelable
