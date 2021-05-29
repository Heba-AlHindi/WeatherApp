package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CurrentForecastEntity(
    var currentTime: Long = 0,

    var currentTemp: Float = 0F,

    var currentFeelsLike: Float = 0F,

    var currentPressure: Int = 0,

    var currentHumidity: Int = 0,

    var currentWindSpeed: Int = 0,

    var currentVisibilityDegree: Int = 0,

    var currentWeather: RealmList<WeatherEntity>? = null
) : RealmObject()






