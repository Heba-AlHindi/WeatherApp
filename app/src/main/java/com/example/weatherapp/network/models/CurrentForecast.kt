package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.parcelize.Parcelize

@RealmClass(embedded = true)
@Parcelize
data class CurrentForecast(
    @SerializedName("coord")
    val coord: Coordination? = null,

    @SerializedName("sys")
    val sys: CityInfo? = null,

    @SerializedName("weather")
    val weather: List<Weather?>? = null,

    @SerializedName("main")
    val main: MainForecast? = null,

    @SerializedName("wind")
    val wind: Wind? = null,

    @SerializedName("clouds")
    val clouds: Clouds? = null,

    @SerializedName("dt")
    val dt: Int? = null,

    @SerializedName("visibility")
    val visibility: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,
) : Parcelable, RealmObject()





