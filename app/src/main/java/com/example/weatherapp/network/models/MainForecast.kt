package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.parcelize.Parcelize
@RealmClass(embedded = true)
@Parcelize
data class MainForecast(
    @SerializedName( "temp")
    val temp: Double?,

    @SerializedName("temp_min")
    var temp_min: Double?,

    @SerializedName("temp_max")
    var temp_max: Double?,

    @SerializedName("feels_like")
    var feels_like: Double?,

    @SerializedName( "humidity")
    val humidity: Int?,

    @SerializedName("pressure")
    val pressure: Double?,
) : Parcelable , RealmObject(){

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String {
        return humidity.toString() + "°"
    }

    fun getTempMinString(): String {
        return temp_min.toString().substringBefore(".") + "°"
    }

    fun getTempMaxString(): String {
        return temp_max.toString().substringBefore(".") + "°"
    }
}
