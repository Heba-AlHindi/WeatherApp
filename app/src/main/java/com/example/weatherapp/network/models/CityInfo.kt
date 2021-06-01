package com.example.weatherapp.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.parcelize.Parcelize
@Parcelize
data class CityInfo(
    @SerializedName("country")
    val country: String? = null,

    @SerializedName("timezone")
    val timezone: String? = null
) : Parcelable
