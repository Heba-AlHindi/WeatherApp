package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    @SerializedName( "deg")
    val degree: Double?,

    @SerializedName("speed")
    val speed: Double?
) : Parcelable
