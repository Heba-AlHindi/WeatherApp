package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeelsLike(
    @SerializedName("day")
    val day: Float,

    @SerializedName("night")
    val night: Float,

    @SerializedName("eve")
    val eve: Float,

    @SerializedName("morn")
    val morn: Float,
) : Parcelable
