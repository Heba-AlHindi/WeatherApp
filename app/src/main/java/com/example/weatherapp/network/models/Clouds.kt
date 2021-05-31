package com.example.weatherapp.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "all")
    val all: Int?
) : Parcelable
