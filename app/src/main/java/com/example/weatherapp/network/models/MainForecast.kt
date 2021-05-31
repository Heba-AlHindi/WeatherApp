package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainForecast(
    @SerializedName( "temp")
    val temp: Double?,

    @SerializedName("temp_min")
    var tempMin: Double?,

    @SerializedName("temp_max")
    var tempMax: Double?,

    @SerializedName("feels_like")
    var feelsLike: Double?,

    @SerializedName( "humidity")
    val humidity: Int?,

    @SerializedName("pressure")
    val pressure: Double?,
) : Parcelable {

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String {
        return humidity.toString() + "°"
    }

    fun getTempMinString(): String {
        return tempMin.toString().substringBefore(".") + "°"
    }

    fun getTempMaxString(): String {
        return tempMax.toString().substringBefore(".") + "°"
    }
}
