package com.example.weatherapp.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CityInfo(
    @Json(name = "country")
    val country: String? = null,

    @Json(name = "timezone")
    val timezone: String? = null
) : Parcelable
