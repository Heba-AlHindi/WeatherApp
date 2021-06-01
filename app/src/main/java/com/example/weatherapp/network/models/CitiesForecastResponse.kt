package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import kotlinx.parcelize.Parcelize

@Parcelize
data class CitiesForecastResponse(
    @SerializedName("cnt")
    val cnt: Int = 0,

    @SerializedName("list")
    val list: List<CurrentForecast>? = null,
) : Parcelable
