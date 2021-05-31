package com.example.weatherapp.network.utils

import android.os.SystemClock
import java.util.concurrent.TimeUnit

/**
 * class to mange whether to fetch data or not depends on lastFetched Time
 */
class RateLimiter<in KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = HashMap<KEY, Long>()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = timestamps[key]
        val currentTime = currentTime()
        return when {
            lastFetched == null -> {
                timestamps[key] = currentTime
                true
            }
            currentTime - lastFetched > timeout -> {
                timestamps[key] = currentTime
                true
            }
            else -> {
                false
            }
        }
    }

    private fun currentTime() = SystemClock.uptimeMillis()

    @Synchronized
    fun reset(key: KEY) {
        timestamps.remove(key)
    }
}