package com.example.weatherapp.database.models

import com.example.weatherapp.database.models.cityCurrentForecast.CurrentForecastEntity
import io.realm.RealmList
import io.realm.RealmObject

open class ForecastEntity(
    var lat: Float = 0F,

    var lon: Float = 0F,

    var timezone: String? = null,

    var currentForecast: CurrentForecastEntity? = null,

    var hourlyForecast: RealmList<HourlyForecastEntity>? = null,

    var dailyForecast: RealmList<DailyForecastEntity>? = null,
) : RealmObject()

