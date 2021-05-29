package com.example.weatherapp

import android.app.Application
import io.realm.Realm

/**
 *  AppApplication Overrides Android Application
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // configure realm
        Realm.init(this)
    }
}