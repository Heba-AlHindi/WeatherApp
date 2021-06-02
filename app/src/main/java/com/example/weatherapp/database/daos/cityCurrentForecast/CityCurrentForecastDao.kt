package com.example.weatherapp.database.daos.cityCurrentForecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.database.asLiveData
import com.example.weatherapp.database.models.cityCurrentForecast.CitiesForecastEntity
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.google.gson.Gson
import io.realm.Realm


class CityCurrentForecastDao {

    fun insert(response: CitiesForecastResponse) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync {
            val json = Gson().toJson(response)
            val model = realm.createObjectFromJson(CitiesForecastEntity::class.java, json)
            clear()
            model?.let {
                realm.insertOrUpdate(model)
            }
        }
        realm.close()
    }

    fun getCitiesCurrent(): LiveData<CitiesForecastEntity> {
        val realm = Realm.getDefaultInstance()
        val data = MutableLiveData<CitiesForecastEntity>()
        val realmResults = realm.where(CitiesForecastEntity::class.java).findAllAsync().asLiveData()
        data.postValue(realmResults.value?.first())
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