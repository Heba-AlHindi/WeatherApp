package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class FeelsLikeEntity(
    var day: Float = 0F,

    var night: Float = 0F,

    var eve: Float = 0F,

    var morn: Float = 0F,
) : RealmObject()
