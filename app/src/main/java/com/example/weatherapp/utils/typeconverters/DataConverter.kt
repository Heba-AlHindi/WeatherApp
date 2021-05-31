package com.example.weatherapp.utils.typeconverters

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object TypeConverter {

//    @JvmStatic
//    fun stringToList(data: String?): List<ListItem>? {
//        if (data == null) {
//            return emptyList()
//        }
//
//        val moshi = Moshi.Builder().build()
//        val type = Types.newParameterizedType(List::class.java, ListItem::class.java)
//        val adapter = moshi.adapter<List<ListItem>>(type)
//        return adapter.fromJson(data)
//    }
//
//    @JvmStatic
//    fun listToString(objects: List<ListItem>): String {
//        val moshi = Moshi.Builder().build()
//        val type = Types.newParameterizedType(List::class.java, ListItem::class.java)
//        val adapter = moshi.adapter<List<ListItem>>(type)
//        return adapter.toJson(objects)
//    }
//
//    @JvmStatic
//    fun weatherStringToList(data: String?): List<WeatherItem>? {
//        if (data == null) {
//            return emptyList()
//        }
//
//        val moshi = Moshi.Builder().build()
//        val type = Types.newParameterizedType(List::class.java, WeatherItem::class.java)
//        val adapter = moshi.adapter<List<WeatherItem>>(type)
//        return adapter.fromJson(data)
//    }
//
//    @JvmStatic
//    fun weatherListToString(objects: List<WeatherItem>): String {
//        val moshi = Moshi.Builder().build()
//        val type = Types.newParameterizedType(List::class.java, WeatherItem::class.java)
//        val adapter = moshi.adapter<List<WeatherItem>>(type)
//        return adapter.toJson(objects)
//    }
}