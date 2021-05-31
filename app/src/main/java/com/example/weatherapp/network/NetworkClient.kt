package com.example.weatherapp.network

import android.util.Log
import com.example.weatherapp.Constants
import com.example.weatherapp.network.services.ForecastApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 *  NetworkClient initialize retrofit services
 *  #1 forecastService
 */
object NetworkClient {

     fun retrofit(): Retrofit {
        Log.e("RETROFIT", Constants.Network.BASE_URL)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttp = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.Network.BASE_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val forecastService: ForecastApi by lazy {
        retrofit().create(ForecastApi::class.java)
    }
}