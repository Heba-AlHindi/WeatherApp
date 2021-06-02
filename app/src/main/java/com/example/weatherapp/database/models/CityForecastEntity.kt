package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject

open class CityForecastEntity(
    var lat: Float = 0F,

    var lon: Float = 0F,

    var timezone: String? = null,

    var hourly: RealmList<HourlyForecastEntity>? = null,

    var daily: RealmList<DailyForecastEntity>? = null,
) : RealmObject()

