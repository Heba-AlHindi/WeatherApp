package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CityInfoEntity(
    var country: String? = null,

    var timezone: String? = null
) : RealmObject()