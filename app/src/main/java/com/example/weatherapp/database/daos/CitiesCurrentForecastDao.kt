package com.example.weatherapp.database.daos

import androidx.lifecycle.LiveData
import com.example.weatherapp.database.LiveRealmObject
import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.google.gson.Gson
import io.realm.Realm


class CitiesCurrentForecastDao {

    private val _current: LiveRealmObject<CitiesForecastEntity> = LiveRealmObject(null)
    val current: LiveData<CitiesForecastEntity>
        get() = _current

    fun insert(response: CitiesForecastResponse) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val json = Gson().toJson(response)
            val model = realm.createObjectFromJson(CitiesForecastEntity::class.java, json)
            model?.let {
                realm.insertOrUpdate(model)
            }
        }
    }

    fun getCitiesCurrent(): LiveData<CitiesForecastEntity> {
        val realm = Realm.getDefaultInstance()
        val realmResults = realm?.where(CitiesForecastEntity::class.java)?.findAll()
        // handle edge case when first lunching the app
        if (realmResults?.size == 0) {
            realm.executeTransaction { transactionRealm ->
                val counter = CitiesForecastEntity()
                transactionRealm.insert(counter)
            }
        }
        val arrayListOfUnmanagedObjects: List<CitiesForecastEntity> =
            realm.copyFromRealm(realmResults)
        this._current.postValue(arrayListOfUnmanagedObjects[0])
        return current
    }

    fun clear() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val result = it.where(CitiesForecastEntity::class.java).findAll()
            result.deleteAllFromRealm()
        }
    }
}