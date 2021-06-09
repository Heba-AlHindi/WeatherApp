package com.example.weatherapp.network.utils

import android.content.SharedPreferences
import android.os.SystemClock
import android.util.Log
import com.example.weatherapp.AppApplication
import com.example.weatherapp.database.SharedPrefHandler.clearValues
import com.example.weatherapp.database.SharedPrefHandler.locationsLastFetched
import java.util.concurrent.TimeUnit

/**
 * class to mange whether to fetch data or not depends on lastFetched Time
 */
class RateLimiter<in KEY>(timeout: Int, timeUnit: TimeUnit) {

    /** can be used at the time user is using the app initialized depends on your situation
     * (e.g) I want my weather to be updated every 10 min's
     *  private val timeout = timeUnit.toMillis(timeout.toLong()) **/

//    private val timestamps = HashMap<KEY, Long>()
//    private val timeout = timeUnit.toMillis(timeout.toLong())

    private val prefs: SharedPreferences by lazy {
        AppApplication.prefs!!
    }

    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = prefs.locationsLastFetched
        val currentTime = currentTime()
        Log.e("lastFetched = ", "${lastFetched / 1000}")
        Log.e("currentTime = ", "${currentTime / 1000}")
        return when {
            // first lunch
            lastFetched == 0L -> {
                prefs.locationsLastFetched = currentTime
                true
            }
            // last time fetched more than required time
            lastFetched > timeout -> {
                prefs.locationsLastFetched = currentTime
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
        prefs.clearValues
    }
}