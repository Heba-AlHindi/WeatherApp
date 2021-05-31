package com.example.weatherapp.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.base.BaseActivity
import com.example.weatherapp.ui.home.HomeViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override fun getViewModelClass() = MainViewModel::class.java

    override fun setUpViews() {
        super.setUpViews()
        // setup bottom nav
        val navController = findNavController(R.id.navControllerFragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}
