package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class TempEntity(
    var dayTemp: Float = 0F,

    var dayMinTemp: Float = 0F,

    var dayMaxTemp: Float = 0F,

    var dayNightTemp: Float = 0F,

    var dayEveTemp: Float = 0F,

    var dayMorn: Float = 0F,
) : RealmObject()