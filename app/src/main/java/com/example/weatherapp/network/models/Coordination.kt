package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordination(
    @SerializedName("lon")
    val lon: Double?,

    @SerializedName("lat")
    val lat: Double?
) : Parcelable
