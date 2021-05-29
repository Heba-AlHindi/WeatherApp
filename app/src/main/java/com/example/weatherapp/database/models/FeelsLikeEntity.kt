package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class FeelsLikeEntity(
    var dayTemp: Float = 0F,

    var dayNightTemp: Float = 0F,

    var dayEveTemp: Float = 0F,

    var dayMornTemp: Float = 0F,
) : RealmObject()
