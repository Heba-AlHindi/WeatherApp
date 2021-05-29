package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
class HourlyForecastEntity(

    val time: Long = 0L,


    val temp: Float = 0F,


    val feelsLike: Float = 0F,


    val pressure: Int = 0,


    val humidity: Int = 0,


    val windSpeed: Int = 0,


    val visibilityDegree: Int = 0,

    val weather: List<WeatherEntity>
) : RealmObject()