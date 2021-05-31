package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class MainForecastEntity(
    var temp: Double = 0.0,

    var tempMin: Double = 0.0,

    var tempMax: Double = 0.0,

    var feelsLike: Double = 0.0,

    var humidity: Int = 0,

    var pressure: Double = 0.0,
) : RealmObject()