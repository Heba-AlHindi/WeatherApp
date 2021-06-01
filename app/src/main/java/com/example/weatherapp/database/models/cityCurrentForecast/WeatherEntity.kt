package com.example.weatherapp.database.models.cityCurrentForecast

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class WeatherEntity(
    var id: Int = 0,

    var main: String? = null,

    var description: String? = null,

    var icon: String? = null,
) : RealmObject()