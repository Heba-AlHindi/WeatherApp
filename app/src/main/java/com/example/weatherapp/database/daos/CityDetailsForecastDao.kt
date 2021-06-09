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
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                val json = Gson().toJson(response)
                val model = it.createObjectFromJson(CityForecastEntity::class.java, json)
                model?.let {
                    realm.insertOrUpdate(model)
                }
            }
        }
    }

    fun getCityDetails(): CityForecastEntity {

        Realm.getDefaultInstance().use {
            val realmResults = it?.where(CityForecastEntity::class.java)?.findAll()
            // handle empty edge case
            if (realmResults?.size == 0) {
                it.executeTransaction { transactionRealm ->
                    val entity = CityForecastEntity()
                    transactionRealm.insert(entity)
                }
            }
            val arrayListOfUnmanagedObjects: List<CityForecastEntity> =
                it.copyFromRealm(realmResults)
            return arrayListOfUnmanagedObjects[0]
        }
    }

    fun clear() {
        Realm.getDefaultInstance().use { it ->
            it.executeTransaction {
                val result = it.where(CityForecastEntity::class.java).findAll()
                result.deleteAllFromRealm()
            }
        }
    }
}