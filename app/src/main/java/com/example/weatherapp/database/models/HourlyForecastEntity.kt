package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class HourlyForecastEntity(
    var dt: Long = 0,

    var temp: Float = 0F,

    var feels_like: Float = 0F,

    var pressure: Int = 0,

    var humidity: Int = 0,

    var wind_speed: Double = 0.0,

    var wind_deg: Int = 0,

    var visibility: Int = 0,

    var weather: RealmList<WeatherEntity>? = null
) : RealmObject(){

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
