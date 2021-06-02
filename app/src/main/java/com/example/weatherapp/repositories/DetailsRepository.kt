package com.example.weatherapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.database.daos.CitiesCurrentForecastDao
import com.example.weatherapp.database.daos.CityDetailsForecastDao
import com.example.weatherapp.database.models.CityForecastEntity
import com.example.weatherapp.network.datasources.CityForecastRemoteDataSource
import com.example.weatherapp.network.models.CityForecastResponse
import com.example.weatherapp.network.utils.NetworkRequestHandler
import com.example.weatherapp.network.utils.RateLimiter
import com.example.weatherapp.network.utils.Resource
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class DetailsRepository {

    private val remoteDataSource: CityForecastRemoteDataSource =
        CityForecastRemoteDataSource()

    private val localeDataSource: CityDetailsForecastDao =
        CityDetailsForecastDao()

    private val repoListRateLimit = RateLimiter<String>(30, TimeUnit.MINUTES)

    fun getCityDetailsForecast(
        key: String,
        lat: Double,
        lon: Double
    ): LiveData<Resource<CityForecastEntity>> {
        return object : NetworkRequestHandler<CityForecastEntity, CityForecastResponse>() {
            override fun saveCallResult(item: CityForecastResponse) {
                localeDataSource.clear()
                return localeDataSource.insert(item)
            }

            override fun shouldFetch(data: CityForecastEntity?): Boolean {
                Log.e("LocationRepository", "shouldFetch()")
                return true
            }

            override fun loadFromDb(): LiveData<CityForecastEntity> {
                Log.e("LocationRepository", "loadFromDb()")
                return localeDataSource.getCityDetails()
            }

            override fun fetchData(): Single<CityForecastResponse> {
                Log.e("LocationRepository", "fetchData()")
                return remoteDataSource.callService(lat, lon)
            }

            override fun onFetchFailed() {
                Log.e("LocationRepository", "onFetchFailed()")
            }

        }.asLiveData
    }
}