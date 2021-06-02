package com.example.weatherapp.database.models.cityCurrentForecast

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CityInfoEntity(
    var country: String? = null,

    var timezone: String? = null,

    var sunrise: Int = 0,

    var sunset: Int = 0,
) : RealmObject()
