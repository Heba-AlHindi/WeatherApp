package com.example.weatherapp.database.models


import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.parcelize.Parcelize

@Parcelize
@RealmClass(embedded = true)
open class CoordinationEntity(
    var lon: Double = 0.0,

    var lat: Double = 0.0
) : Parcelable, RealmObject()