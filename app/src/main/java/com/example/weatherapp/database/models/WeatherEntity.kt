package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class WeatherEntity(
    var id: Int = 0,

    var mainWeather: String? = null,

    var description: String? = null,

    var icon: String? = null,
) : RealmObject()