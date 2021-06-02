package com.example.weatherapp.ui.main

import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.base.BaseActivity

/**
 *  MainActivity is the entry for app navigation
 *  holds navHostFragment
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun getViewModelClass() = MainViewModel::class.java
}
