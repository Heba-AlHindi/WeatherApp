package com.example.weatherapp.database.models

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.parcelize.Parcelize

@Parcelize
@RealmClass(embedded = true)
open class MainForecastEntity(
    var temp: Double = 0.0,

    var temp_min: Double = 0.0,

    var temp_max: Double = 0.0,

    var feels_like: Double = 0.0,

    var humidity: Int = 0,

    var pressure: Double = 0.0,
) : Parcelable, RealmObject()