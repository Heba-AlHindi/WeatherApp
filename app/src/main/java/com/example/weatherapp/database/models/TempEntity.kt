package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class TempEntity(
    var day: Float = 0F,

    var min: Float = 0F,

    var max: Float = 0F,

    var night: Float = 0F,

    var eve: Float = 0F,

    var morn: Float = 0F,
) : RealmObject()