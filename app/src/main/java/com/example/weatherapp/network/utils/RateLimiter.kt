package com.example.weatherapp.network.utils

import android.content.SharedPreferences
import android.util.Log
import com.example.weatherapp.AppApplication
import com.example.weatherapp.Constants
import com.example.weatherapp.database.SharedPrefHandler.cityLastFetched
import com.example.weatherapp.database.SharedPrefHandler.clearValues
import com.example.weatherapp.database.SharedPrefHandler.locationsLastFetched
import java.util.concurrent.TimeUnit

/**
 * class to mange whether to fetch data or not depends on lastFetched Time
 */
class RateLimiter<in KEY>(private val timeout: Int) {

    /** this process of refreshing used depends on your situation
     * (e.g) I want my weather to be updated every 10 min's **/

    private val prefs: SharedPreferences by lazy {
        AppApplication.prefs!!
    }

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = when (key) {
            Constants.Prefs.LOCATIONS_LAST_FETCHED -> prefs.locationsLastFetched
            Constants.Prefs.CITY_LAST_FETCHED -> prefs.cityLastFetched
            else -> 0L
        }
        val currentTime = currentTime()
        Log.e("lastFetched = ", "$lastFetched")
        Log.e("currentTime = ", "$currentTime")
        Log.e("timeout = ", "$timeout")
        return when {
            // first lunch
            lastFetched == 0L -> {
                prefs.locationsLastFetched = currentTime
                true
            }
            // last time fetched more than required time
            timeDiff(currentTime, lastFetched) >= timeout -> {
                true
            }
            else -> {
                false
            }
        }
    }

    private fun currentTime() = System.currentTimeMillis()

    private fun timeDiff(current: Long, lastFetched: Long): Long {
        val diff = current - lastFetched
        val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diff)
        Log.e("timeDiff = ", "$diffInMin")
        return diffInMin
    }

    @Synchronized
    fun reset(key: KEY) {
        prefs.clearValues
    }
}