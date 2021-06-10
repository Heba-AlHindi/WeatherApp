package com.example.weatherapp.ui.base

import com.example.weatherapp.network.utils.RateLimiter

abstract class WeatherRepository {
    private val repoListRateLimit = RateLimiter<String>(5)

    fun notUpdated(key: String): Boolean {
        return repoListRateLimit.shouldFetch(key)
    }
}