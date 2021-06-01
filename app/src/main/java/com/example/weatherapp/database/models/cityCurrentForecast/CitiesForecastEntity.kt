package com.example.weatherapp.database.models.cityCurrentForecast

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.RealmModule

open class CitiesForecastEntity(
    var citiesNo: Int = 0,

    var citiesCurrentForecast: RealmList<CurrentForecastEntity>? = null,
) : RealmModel
