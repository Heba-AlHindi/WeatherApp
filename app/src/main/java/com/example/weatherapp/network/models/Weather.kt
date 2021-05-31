package com.example.weatherapp.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "id")
    val id: Int?,

    @Json(name = "main")
    val main: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "icon")
    val icon: String?,
) : Parcelable