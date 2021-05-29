package com.example.weatherapp.database.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
data class TempEntity(

    val dayTemp: Float = 0F,


    val dayMin: Float = 0F,


    val dayMax: Float = 0F,


    val dayNight: Float = 0F,


    val dayEve: Float = 0F,


    val dayMorn: Float = 0F,
): RealmObject()