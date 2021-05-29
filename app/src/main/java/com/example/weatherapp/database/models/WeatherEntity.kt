package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class WeatherEntity(
    val main: String = "",

    val description: String = "",

    val icon: String?,
) : RealmObject()