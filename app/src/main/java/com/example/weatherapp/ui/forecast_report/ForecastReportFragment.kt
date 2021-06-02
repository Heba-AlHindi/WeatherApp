package com.example.weatherapp.ui.forecast_report

import com.example.weatherapp.databinding.ForecastReportFragmentBinding
import com.example.weatherapp.ui.base.BaseFragment

/**
 *  ForecastReportFragment Contains Hourly && Daily Forecast
 */
class ForecastReportFragment :
    BaseFragment<ForecastReportFragmentBinding, ForecastReportViewModel>() {

    override fun getViewBinding() = ForecastReportFragmentBinding.inflate(layoutInflater)

    override fun getViewModelClass() = ForecastReportViewModel::class.java
}