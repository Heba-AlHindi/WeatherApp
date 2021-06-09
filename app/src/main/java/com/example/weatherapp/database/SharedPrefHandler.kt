package com.example.weatherapp.database

import android.content.Context
import android.content.SharedPreferences

object SharedPrefHandler {

    private const val LOCATIONS_LAST_FETCHED = "LOCATIONS_LAST_FETCHED"

    fun sharedPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editPref(operation: (SharedPreferences.Editor) -> Unit) {
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
        get() = getLong(LOCATIONS_LAST_FETCHED, 0L)
        set(value) {
            editPref {
                it.put(LOCATIONS_LAST_FETCHED to value)
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
