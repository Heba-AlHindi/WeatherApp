package com.example.weatherapp.database.models

import android.os.Build
import androidx.annotation.RequiresApi
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.RealmField
import java.util.*

@RealmClass(embedded = true)
open class DailyForecastEntity(
    var dt: Long = 0L,

    var temp: TempEntity? = null,

    var feels_like: FeelsLikeEntity? = null,

    var pressure: Int = 0,

    var humidity: Int = 0,

    var wind_speed: Double = 0.0,

    var weather: RealmList<WeatherEntity>? = null
) : RealmObject() {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is DailyForecastEntity -> {
                this.dt == other.dt
            }
            else -> false
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun hashCode(): Int {
        return Objects.hash(dt)
    }
}
