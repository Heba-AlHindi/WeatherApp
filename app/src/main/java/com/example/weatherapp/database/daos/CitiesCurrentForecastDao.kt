package com.example.weatherapp.database.daos

import androidx.lifecycle.LiveData
import com.example.weatherapp.database.LiveRealmObject
import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.google.gson.Gson
import io.realm.Realm

class CitiesCurrentForecastDao {

//    private val _current: LiveRealmObject<CitiesForecastEntity> = LiveRealmObject(null)
//    val current: LiveData<CitiesForecastEntity>
//        get() = _current

    fun insert(response: CitiesForecastResponse) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                val json = Gson().toJson(response)
                val model = it.createObjectFromJson(CitiesForecastEntity::class.java, json)
                model?.let {
                    realm.insertOrUpdate(model)
                }
            }
        }
    }

    fun getCitiesCurrent(): CitiesForecastEntity {
        Realm.getDefaultInstance().use {
            val realmResults = it?.where(CitiesForecastEntity::class.java)?.findAll()
            // handle edge case when first lunching the app no database yet
            if (realmResults.isNullOrEmpty() || realmResults.size == 0) {
                it.executeTransaction { transactionRealm ->
                    val entity = CitiesForecastEntity() // new object
                    transactionRealm.insert(entity)
                }
            }
            val arrayListOfUnmanagedObjects: List<CitiesForecastEntity> =
                it.copyFromRealm(realmResults)
            return arrayListOfUnmanagedObjects[0]
        }
    }

    fun clear() {
        Realm.getDefaultInstance().use { it ->
            it.executeTransaction {
                val result = it.where(CitiesForecastEntity::class.java).findAll()
                result.deleteAllFromRealm()
            }
        }
    }
}