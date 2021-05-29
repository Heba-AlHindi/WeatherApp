package com.example.weatherapp.ui.main

import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}