package com.example.weatherapp.ui.home

import com.example.weatherapp.databinding.HomeFragmentBinding
import com.example.weatherapp.ui.base.BaseFragment

/**
 *  HomeFragment Contains Current && Hourly Forecast
 */
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override fun getViewBinding() = HomeFragmentBinding.inflate(layoutInflater)

    override fun getViewModelClass() = HomeViewModel::class.java
}