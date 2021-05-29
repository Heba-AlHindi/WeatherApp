package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class DailyForecastEntity(
    var dailyTime: Long = 0L,

    var dailyTemp: TempEntity? = null,

    var dailyFeelsLike: FeelsLikeEntity? = null,

    var dailyPressure: Int = 0,

    var dailyHumidity: Int = 0,

    var dailyWindSpeed: Int = 0,

    var dailyWeather: RealmList<WeatherEntity>? = null
) : RealmObject()
