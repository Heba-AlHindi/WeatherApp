package com.example.weatherapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.database.daos.CityDetailsForecastDao
import com.example.weatherapp.database.models.CityForecastEntity
import com.example.weatherapp.network.datasources.CityForecastRemoteDataSource
import com.example.weatherapp.network.models.CityForecastResponse
import com.example.weatherapp.network.utils.NetworkRequestHandler
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.ui.base.WeatherRepository
import io.reactivex.rxjava3.core.Single

class DetailsRepository : WeatherRepository() {

    private val remoteDataSource: CityForecastRemoteDataSource =
        CityForecastRemoteDataSource()

    private val localeDataSource: CityDetailsForecastDao =
        CityDetailsForecastDao()

    fun getCityDetailsForecast(
        key: String,
        lat: Double,
        lon: Double
    ): LiveData<Resource<CityForecastEntity>> {
        return object : NetworkRequestHandler<CityForecastEntity, CityForecastResponse>(key) {
            override fun saveCallResult(item: CityForecastResponse) {
                localeDataSource.clear()
                return localeDataSource.insert(item)
            }

            override fun shouldFetch(data: CityForecastEntity): Boolean {
                val isDataEmpty = data.daily!!.size == 0
                Log.e("DetailsRepository", "isDataEmpty : $isDataEmpty")
                return isDataEmpty || notUpdated(key)
            }

            override fun loadFromDb(): CityForecastEntity {
                Log.e("DetailsRepository", "loadFromDb()")
                return localeDataSource.getCityDetails()
            }

            override fun fetchData(): Single<CityForecastResponse> {
                Log.e("DetailsRepository", "fetchData()")
                return remoteDataSource.callService(lat, lon)
            }

            override fun onFetchFailed() {
                Log.e("DetailsRepository", "onFetchFailed()")
            }

        }.asLiveData
    }
}