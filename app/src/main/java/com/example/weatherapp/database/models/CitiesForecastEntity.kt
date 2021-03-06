package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject

open class CitiesForecastEntity(
    var cnt: Int = 0,

    var list: RealmList<CurrentForecastEntity>? = null,
) : RealmObject()
