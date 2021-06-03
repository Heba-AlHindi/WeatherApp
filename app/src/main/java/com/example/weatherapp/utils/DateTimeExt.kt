package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun getHourOfDay(timeUnix: Long): String {
    val date = Date(timeUnix*1000)
    val cal = Calendar.getInstance()
    cal.time = date
    val hours = cal.get(Calendar.HOUR_OF_DAY)
    return "$hours:00"
}


fun getDay(timeUnix: Long): String {
    val dateFormat = SimpleDateFormat("MM/dd")
    val newDate = Date(timeUnix*1000)
    return dateFormat.format(newDate)
}