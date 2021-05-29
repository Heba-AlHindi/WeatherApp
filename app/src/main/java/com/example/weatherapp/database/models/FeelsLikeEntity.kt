package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
data class FeelsLikeEntity(

    val dayTemp: Float = 0F,


    val dayNight: Float = 0F,


    val dayEve: Float = 0F,


    val dayMorn: Float = 0F,
) : RealmObject()
