package com.example.weatherapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 *  AppApplication Overrides Android Application
 */
class AppApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        // init realm for whole project
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}