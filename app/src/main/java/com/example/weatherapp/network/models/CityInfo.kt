package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityInfo(
    @SerializedName("country")
    val country: String? = null,

    @SerializedName("timezone")
    val timezone: String? = null
) : Parcelable
