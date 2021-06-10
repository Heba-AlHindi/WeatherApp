package com.example.weatherapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.database.daos.CitiesCurrentForecastDao
import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.network.datasources.CitiesForecastRemoteDataSource
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.example.weatherapp.network.utils.NetworkRequestHandler
import com.example.weatherapp.network.utils.RateLimiter
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.ui.base.WeatherRepository
import io.reactivex.rxjava3.core.Single

class LocationRepository : WeatherRepository() {

    private val remoteDataSource: CitiesForecastRemoteDataSource =
        CitiesForecastRemoteDataSource()

    private val localeDataSource: CitiesCurrentForecastDao =
        CitiesCurrentForecastDao()

    fun getCitiesForecast(key: String): LiveData<Resource<CitiesForecastEntity>> {
        return object : NetworkRequestHandler<CitiesForecastEntity, CitiesForecastResponse>(key) {
            override fun saveCallResult(item: CitiesForecastResponse) {
                localeDataSource.clear()
                return localeDataSource.insert(item)
            }

            override fun shouldFetch(data: CitiesForecastEntity): Boolean {
                val isDataEmpty = data.list!!.size == 0
                Log.e("LocationRepository", "isDataEmpty : $isDataEmpty")
                return isDataEmpty || notUpdated(key)
            }

            override fun loadFromDb(): CitiesForecastEntity {
                Log.e("LocationRepository", "loadFromDb()")
                return localeDataSource.getCitiesCurrent()
            }

            override fun fetchData(): Single<CitiesForecastResponse> {
                Log.e("LocationRepository", "fetchData()")
                return remoteDataSource.callService()
            }

            override fun onFetchFailed() {
                Log.e("LocationRepository", "onFetchFailed()")
            }

        }.asLiveData
    }
}