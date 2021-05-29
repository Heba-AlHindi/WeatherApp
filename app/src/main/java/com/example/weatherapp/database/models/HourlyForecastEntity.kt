package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class HourlyForecastEntity(
    var hourlyTime: Long = 0,

    var hourlyTemp: Float = 0F,

    var hourlyFeelsLike: Float = 0F,

    var hourlyPressure: Int = 0,

    var hourlyHumidity: Int = 0,

    var hourlyWindSpeed: Int = 0,

    var hourlyVisibilityDegree: Int = 0,

    var hourlyWeather: RealmList<WeatherEntity>? = null
) : RealmObject()