package com.example.weatherapp.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class CurrentForecast(
    @Json(name = "coord")
    val coordination: Coordination? = null,

    @Json(name = "sys")
    val cityInfo: CityInfo? = null,

    @Json(name = "weather")
    val weather: List<Weather?>? = null,

    @Json(name = "main")
    val mainForecast: MainForecast? = null,

    @Json(name = "wind")
    val wind: Wind? = null,

    @Json(name = "clouds")
    val clouds: Clouds? = null,

    @Json(name = "dt")
    val currentTime: Int? = null,

    @Json(name = "visibility")
    val visibility: Int? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null,
) : Parcelable





