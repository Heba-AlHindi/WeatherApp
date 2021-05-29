package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
class DailyForecastEntity(

    val time: Long = 0L,


    val temp: TempEntity,


    val feelsLike: FeelsLikeEntity,


    val pressure: Int = 0,


    val humidity: Int = 0,


    val windSpeed: Int = 0,


    val weather: List<WeatherEntity>
): RealmObject()
