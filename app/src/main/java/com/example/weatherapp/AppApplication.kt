package com.example.weatherapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.weatherapp.database.SharedPrefHandler.sharedPreference
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

    companion object {
        var prefs: SharedPreferences? = null
        lateinit var instance: AppApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        // init sharedPref
        instance = this
        prefs = sharedPreference(applicationContext, Constants.Prefs.PREFS_NAME)

        // init realm for whole project
        Realm.init(this)
        // default configuration
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}