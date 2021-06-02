package com.example.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.database.models.DailyForecastEntity
import com.example.weatherapp.database.models.HourlyForecastEntity
import com.example.weatherapp.database.models.MainForecastEntity
import com.example.weatherapp.databinding.DetailsFragmentBinding
import com.example.weatherapp.network.utils.NetworkStatus
import com.example.weatherapp.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlin.let as let1

/**
 *  HomeFragment Contains Current && Hourly Forecast
 */
class DetailsFragment :
    BaseFragment<DetailsFragmentBinding, DetailsViewModel, DetailsViewModelFactory>() {
    private var lt: Double = 0.0
    private var ln: Double = 0.0
    private var windSpeed: Float = 0F
    private var icon: String = ""
    private var cityName: String = ""
    private var status: String? = ""
    private var currentDetails: MainForecastEntity = MainForecastEntity()

    override fun getViewModelFactory() = DetailsViewModelFactory(lt, ln)

    override fun getViewBinding() = DetailsFragmentBinding.inflate(layoutInflater)

    override fun getViewModelClass() = DetailsViewModel::class.java

    override fun init() {
        super.init()
        initAdapters()
    }

    override fun getArgs() {
        super.getArgs()
        val bundle = arguments
        if (bundle != null) {
            val args = DetailsFragmentArgs.fromBundle(bundle)
            /** save coordination **/
            lt = args.coordination.lat
            ln = args.coordination.lon
            windSpeed = args.windSpeed
            icon = args.icon
            currentDetails = args.currentDetails
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** set data views **/
        binding.tvCity.text = cityName
        binding.tvStatus.text = status
        Picasso.get()
            .load("http://openweathermap.org/img/w/${icon}.png")
            .into(binding.imgForecast)
        val data = windSpeed.let1 { mapDetails(it, currentDetails) }
        updateDetailsAdapter(data)

        viewModel.getCityDetailsForecast(65.00, 65.222).observe(viewLifecycleOwner) {
            when (it.networkStatus) {
                NetworkStatus.LOADING -> {
                }
                NetworkStatus.SUCCESS -> {
                    val hourlyList = it.data?.hourly?.subList(0, it.data.hourly!!.size)
                    val dailyList = it.data?.daily?.subList(0, it.data.daily!!.size)
                    hourlyList?.let1 { it1 -> updateHourlyAdapter(it1) }
                    dailyList?.let1 { it2 -> updateDailyAdapter(it2) }
                }
                NetworkStatus.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initAdapters() {
        // init details
        val adapter = DetailsRecyclerAdapter()
        binding.recForecastData.adapter = adapter
        binding.recForecastData.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        postponeEnterTransition()
        binding.recForecastData.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        // init hourly
        val hourlyAdapter = HourlyRecyclerAdapter()
        binding.recHourlyForecast.adapter = hourlyAdapter
        binding.recHourlyForecast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        postponeEnterTransition()
        binding.recHourlyForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        // init daily
        val dailyAdapter = DailyRecyclerAdapter("")
        binding.recDailyForecast.adapter = dailyAdapter
        binding.recDailyForecast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        postponeEnterTransition()
        binding.recDailyForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun mapDetails(
        windSpeed: Float,
        currentDetails: MainForecastEntity
    ): ArrayList<Pair<String, String>> {
        // make map for weather details info
        val map = mutableMapOf<String, String?>()
        map["Temp"] = currentDetails.temp.toString().substringBefore(".") + "°"
        currentDetails.feels_like.also {
            map["Feels like"] = it.toString().substringBefore(".") + "°"
        }
        map["Wind"] = windSpeed.toString().substringBefore(".") + " meter/sec"
        currentDetails.pressure.also {
            map["Pressure"] = it.toString().substringBefore(".")
        }
        currentDetails.humidity.toDouble()
            .also { map["Humidity"] = it.toString().substringBefore(".") + "%" }
        val data: ArrayList<Pair<String, String>> = arrayListOf()
        map.forEach { (t, v) -> data.add(Pair(t, v.toString())) }
        return data
    }

    private fun updateDetailsAdapter(map: ArrayList<Pair<String, String>>) {
        val adapter = DetailsRecyclerAdapter()
        binding.recForecastData.adapter = adapter
        binding.recForecastData.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        (binding.recForecastData.adapter as DetailsRecyclerAdapter).submitList(map)
        binding.recForecastData.adapter!!.notifyDataSetChanged()

    }

    private fun updateHourlyAdapter(list: List<HourlyForecastEntity>) {
        val adapter = HourlyRecyclerAdapter()
        binding.recHourlyForecast.adapter = adapter
        binding.recHourlyForecast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        (binding.recHourlyForecast.adapter as HourlyRecyclerAdapter).submitList(list)
        binding.recHourlyForecast.adapter!!.notifyDataSetChanged()
    }

    private fun updateDailyAdapter(list: List<DailyForecastEntity>) {
        val adapter = DailyRecyclerAdapter("")
        binding.recDailyForecast.adapter = adapter
        binding.recDailyForecast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        (binding.recDailyForecast.adapter as DailyRecyclerAdapter).submitList(list)
        binding.recDailyForecast.adapter!!.notifyDataSetChanged()
    }

}