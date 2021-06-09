package com.example.weatherapp

/**
 *  Constants holds app const values
 */
object Constants {

    object Network {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val API_KEY_VALUE = "751d80f6c314139192ffcb62c107e654"
    }

    object Forecast {
        const val IDS = "250441,250774,248944"
        const val UNITS = "metric"
        const val EXCLUDE = "minutely,alerts,current"
    }

    object Prefs {
        const val PREFS_NAME = "com.example.weatherapp.preferences"
    }
}