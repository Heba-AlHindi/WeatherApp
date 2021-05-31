package com.example.weatherapp.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MainForecast(
    @Json(name = "temp")
    val temp: Double?,

    @Json(name = "temp_min")
    var tempMin: Double?,

    @Json(name = "temp_max")
    var tempMax: Double?,

    @Json(name = "feels_like")
    var feelsLike: Double?,

    @Json(name = "humidity")
    val humidity: Int?,

    @Json(name = "pressure")
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
