package com.example.weatherapp.database

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.Constants

/**
 *  Manage App SharedPref
 */
object SharedPrefHandler {

    fun sharedPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editPref(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }

    // generic function to save any type
    fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        when (val value = pair.second) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("")
        }
    }

    var SharedPreferences.locationsLastFetched
        get() = getLong(Constants.Prefs.LOCATIONS_LAST_FETCHED, 0L)
        set(value) {
            editPref {
                it.put(Constants.Prefs.LOCATIONS_LAST_FETCHED to value)
            }
        }

    var SharedPreferences.cityLastFetched
        get() = getLong(Constants.Prefs.CITY_LAST_FETCHED, 0L)
        set(value) {
            editPref {
                it.put(Constants.Prefs.CITY_LAST_FETCHED to value)
            }
        }

    // clear sharedPref value
    var SharedPreferences.clearValues
        get() = run { }
        set(value) {
            editPref {
                it.clear()
            }
        }
}
