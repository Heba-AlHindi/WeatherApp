package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun getHourOfDay(timeUnix: Long): String {
    val date: Date = Date(timeUnix)
    val cal = Calendar.getInstance()
    cal.time = date
    val hours = cal.get(Calendar.HOUR_OF_DAY)
    return hours.toString()
}


fun getDay(timeUnix: Long): String {
    val dateFormat = SimpleDateFormat("MM.dd")
    val newDate = Date(timeUnix)
    return dateFormat.format(newDate)
}