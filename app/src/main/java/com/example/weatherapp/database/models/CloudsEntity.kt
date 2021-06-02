package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CloudsEntity(
    var all: Int = 0
) : RealmObject()
