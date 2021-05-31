package com.example.weatherapp.database.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class CurrentForecastEntity(
    var coordination: CoordinationEntity? = null,

    var cityInfo: CityInfoEntity? = null,

    var weather: RealmList<WeatherEntity>? = null,

    var mainForecast: MainForecastEntity? = null,

    var wind: WindEntity? = null,

    var clouds: CloudsEntity? = null,

    var currentTime: Int = 0,

    var visibility: Int = 0,

    var name: String? = null,

    var id: Int = 0,
) : RealmObject()






