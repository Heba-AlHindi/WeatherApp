package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class DailyForecastEntity(
    var dt: Long = 0L,

    var temp: TempEntity? = null,

    var feels_like: FeelsLikeEntity? = null,

    var pressure: Int = 0,

    var humidity: Int = 0,

    var wind_speed: Int = 0,

    var weather: RealmList<WeatherEntity>? = null
) : RealmObject()
