package com.example.weatherapp.database

import com.example.weatherapp.database.daos.cityCurrentForecast.CityCurrentForecastDao
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

// get instance of realm objects dao
fun Realm.CityCurrentForecastDao(): CityCurrentForecastDao = CityCurrentForecastDao()

// convert realm result to LiveData<T>
fun <T: RealmModel> RealmResults<T>.asLiveData() = LiveRealmResults<T>(this)

