package com.example.weatherapp.database.daos

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.database.LiveRealmObject
import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.database.models.CityForecastEntity
import com.example.weatherapp.network.models.CityForecastResponse
import com.google.gson.Gson
import io.realm.Realm

class CityDetailsForecastDao {

    private val _data: LiveRealmObject<CityForecastEntity> = LiveRealmObject(null)
    val data: LiveData<CityForecastEntity>
        get() = _data

    fun insert(response: CityForecastResponse) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val json = Gson().toJson(response)
            val model = realm.createObjectFromJson(CityForecastEntity::class.java, json)
            model?.let {
                realm.insertOrUpdate(model)
            }
        }
    }

    fun getCityDetails(): LiveData<CityForecastEntity> {
        try {
            val realm = Realm.getDefaultInstance()
            val realmResults = realm?.where(CityForecastEntity::class.java)?.findAll()
            // handle empty edge case
            realm.executeTransaction { transactionRealm ->
                val entity = CityForecastEntity()
                transactionRealm.insert(entity)
            }
            val arrayListOfUnmanagedObjects: List<CityForecastEntity> =
                realm.copyFromRealm(realmResults)
            this._data.postValue(arrayListOfUnmanagedObjects[0])
            return data
        } catch (e: Exception) {
            Log.e("getCityDetails() ", e.message.toString())
            return data
        }
    }

    fun clear() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val result = it.where(CityForecastEntity::class.java).findAll()
            result.deleteAllFromRealm()
        }
    }
}