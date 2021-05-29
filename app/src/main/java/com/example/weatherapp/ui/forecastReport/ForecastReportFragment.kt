package com.example.weatherapp.ui.forecastReport

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.ui.base.BaseFragment

class ForecastReportFragment : BaseFragment() {

    companion object {
        fun newInstance() = ForecastReportFragment()
    }

    private lateinit var viewModel: ForecastReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_report_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastReportViewModel::class.java)
    }

}