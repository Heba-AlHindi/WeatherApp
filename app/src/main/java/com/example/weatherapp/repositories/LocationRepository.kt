package com.example.weatherapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.database.daos.cityCurrentForecast.CityCurrentForecastDao
import com.example.weatherapp.database.models.cityCurrentForecast.CitiesForecastEntity
import com.example.weatherapp.network.datasources.CitiesForecastRemoteDataSource
import com.example.weatherapp.network.models.CitiesForecastResponse
import com.example.weatherapp.network.utils.NetworkRequestHandler
import com.example.weatherapp.network.utils.RateLimiter
import com.example.weatherapp.network.utils.Resource
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class LocationRepository {

    private val remoteDataSource: CitiesForecastRemoteDataSource =
        CitiesForecastRemoteDataSource()

    private val repoListRateLimit = RateLimiter<String>(30, TimeUnit.MINUTES)

    fun getCitiesForecast(key: String): LiveData<Resource<CitiesForecastEntity>> {
        return object : NetworkRequestHandler<CitiesForecastEntity, CitiesForecastResponse>() {
            override fun saveCallResult(item: CitiesForecastResponse) {
                CityCurrentForecastDao().clear()
                return CityCurrentForecastDao().insert(item)
            }

            override fun shouldFetch(data: CitiesForecastEntity?): Boolean {
                Log.e("LocationRepository", "shouldFetch()")
                return true
            }

            override fun loadFromDb(): LiveData<CitiesForecastEntity> {
                Log.e("LocationRepository", "loadFromDb()")
                return CityCurrentForecastDao().getCitiesCurrent()
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