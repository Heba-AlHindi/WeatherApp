package com.example.weatherapp.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Coordination(
    @Json(name = "lon")
    val lon: Double?,

    @Json(name = "lat")
    val lat: Double?
) : Parcelable
