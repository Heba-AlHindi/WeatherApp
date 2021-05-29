package com.example.weatherapp.database.models

import io.realm.RealmObject

open class ForecastEntity(

    val lat: Double = 0.0,


    val lon: Double = 0.0,


    val timezone: String = "",


    val current: CurrentForecastEntity,


    val hourly: List<HourlyForecastEntity>,


    val dailyForecast: List<DailyForecastEntity>,
) : RealmObject()

