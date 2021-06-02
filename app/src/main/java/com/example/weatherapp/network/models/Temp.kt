package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temp(
    @SerializedName("day")
    val day: Float,

    @SerializedName( "min")
    val min: Float,

    @SerializedName( "max")
    val max: Float,

    @SerializedName( "night")
    val night: Float,

    @SerializedName( "eve")
    val eve: Float,

    @SerializedName("morn")
    val morn: Float,
) : Parcelable