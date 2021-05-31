package com.example.weatherapp.network.utils

import com.example.weatherapp.network.utils.NetworkStatus.*

/**
 *  A generic class that holds a value with its network status
 */
data class Resource<out T>(val networkStatus: NetworkStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
