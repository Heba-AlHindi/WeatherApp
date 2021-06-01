package com.example.weatherapp.database.models.cityCurrentForecast

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CoordinationEntity(
    var lon: Double = 0.0,

    var lat: Double = 0.0
) : RealmObject()
