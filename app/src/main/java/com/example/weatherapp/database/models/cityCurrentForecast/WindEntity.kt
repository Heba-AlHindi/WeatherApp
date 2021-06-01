package com.example.weatherapp.database.models.cityCurrentForecast

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class WindEntity(
    var degree: Double = 0.0,

    var speed: Double = 0.0
) : RealmObject()
