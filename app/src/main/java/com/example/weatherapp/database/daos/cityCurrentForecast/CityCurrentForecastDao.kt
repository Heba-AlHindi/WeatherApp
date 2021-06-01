package com.example.weatherapp.database.daos.cityCurrentForecast

import androidx.lifecycle.LiveData
import com.example.weatherapp.database.asLiveData
import com.example.weatherapp.database.models.cityCurrentForecast.CitiesForecastEntity
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmResults


class CityCurrentForecastDao() {

    fun insert(response: CitiesForecastResponse) {
        val realm = Realm.getDefaultInstance()
//        realm.executeTransactionAsync {
//            val json = Gson().toJson(response)
//            val model = realm.createObjectFromJson(CitiesForecastEntity::class.java, json)
//            it.insert(model)
//        }
        realm.executeTransactionAsync {
            it.insert(response)
        }
        realm.close()
    }

    fun getCitiesCurrent(): LiveData<RealmResults<CitiesForecastEntity>> {
        val realm = Realm.getDefaultInstance()
        val data = realm.where(CitiesForecastEntity::class.java).findAllAsync().asLiveData()
        realm.close()
        return data
    }

    fun clear() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync {
            val result = it.where(CitiesForecastEntity::class.java).findAll()
            result.deleteAllFromRealm()
        }
        realm.close()
    }

}