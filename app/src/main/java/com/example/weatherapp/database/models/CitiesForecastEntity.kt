package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject

open class CitiesForecastEntity(
    var citiesNo: Int = 0,

    var citiesCurrentForecast: RealmList<CurrentForecastEntity>? = null,
) : RealmObject()