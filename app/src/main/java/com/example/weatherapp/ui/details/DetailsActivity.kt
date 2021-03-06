package com.example.weatherapp.ui.details

import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.database.models.*
import com.example.weatherapp.databinding.ActivityDetailsBinding
import com.example.weatherapp.network.utils.NetworkStatus
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.ui.base.BaseActivity
import com.squareup.picasso.Picasso

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {

    override fun getViewBinding() = ActivityDetailsBinding.inflate(layoutInflater)
    override fun getViewModelClass() = DetailsViewModel::class.java
    private val args: DetailsActivityArgs by navArgs()

    override fun init() {
        super.init()
        initAdapters()
    }

    override fun setUpViews() {
        super.setUpViews()
        /** extract args **/
        val lt = args.coordination.lat
        val ln = args.coordination.lon
        val cityName = args.cityName
        val status = args.main
        val details = args.currentDetails
        val speed = args.windSpeed
        val iconPath = args.icon

        /** set data views **/
        binding.tvCity.text = cityName
        binding.tvStatus.text = status
        Picasso.get()
            .load("http://openweathermap.org/img/w/${iconPath}.png")
            .into(binding.imgForecast)

        val data = mapDetails(speed, details)
        updateDetailsAdapter(data)

        viewModel.getCityDetailsForecast(lt, ln).observe(this) {
            when (it.networkStatus) {
                NetworkStatus.LOADING -> {
                }
                NetworkStatus.SUCCESS -> {
                    manageUI(it)
                }
                NetworkStatus.ERROR -> {
                    manageUI(it)
                }
            }
        }
    }

    private fun manageUI(it: Resource<CityForecastEntity>) {
        val hourlyList = it.data?.hourly?.subList(0, it.data.hourly!!.size / 2)
        val dailyList = it.data?.daily?.subList(0, it.data.daily!!.size)
        hourlyList?.let { it1 -> updateHourlyAdapter(it1) }
        dailyList?.let { it1 -> updateDailyAdapter(it1) }
    }

    private fun initAdapters() {
        // init details
        val adapter = DetailsRecyclerAdapter()
        binding.recForecastData.adapter = adapter
        binding.recForecastData.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        // init hourly
        val hourlyAdapter = HourlyRecyclerAdapter()
        binding.recHourlyForecast.adapter = hourlyAdapter
        binding.recHourlyForecast.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        // init daily
        val dailyAdapter = DailyRecyclerAdapter(args.icon)
        binding.recDailyForecast.adapter = dailyAdapter
        binding.recDailyForecast.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun mapDetails(
        windSpeed: Float,
        currentDetails: MainForecastEntity
    ): ArrayList<Pair<String, String>> {
        // make map for weather details info
        val map = mutableMapOf<String, String?>()
        map[resources.getString(R.string.temp)] =
            currentDetails.temp.toString().substringBefore(".") + "??"
        currentDetails.feels_like.also {
            map[resources.getString(R.string.feels_like)] = it.toString().substringBefore(".") + "??"
        }
        map[resources.getString(R.string.wind)] =
            windSpeed.toString().substringBefore(".") + " meter/sec"
        currentDetails.pressure.also {
            map[resources.getString(R.string.pressure)] = it.toString().substringBefore(".")
        }
        currentDetails.humidity.toDouble()
            .also {
                map[resources.getString(R.string.humidity)] =
                    it.toString().substringBefore(".") + "%"
            }
        val data: ArrayList<Pair<String, String>> = arrayListOf()
        map.forEach { (t, v) -> data.add(Pair(t, v.toString())) }
        return data
    }

    private fun updateDetailsAdapter(map: ArrayList<Pair<String, String>>) {
        val adapter = DetailsRecyclerAdapter()
        binding.recForecastData.adapter = adapter
        binding.recForecastData.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        (binding.recForecastData.adapter as DetailsRecyclerAdapter).submitList(map)
        binding.recForecastData.adapter!!.notifyDataSetChanged()

    }

    private fun updateHourlyAdapter(list: List<HourlyForecastEntity>) {
        val adapter = HourlyRecyclerAdapter()
        binding.recHourlyForecast.adapter = adapter
        binding.recHourlyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        (binding.recHourlyForecast.adapter as HourlyRecyclerAdapter).submitList(list)
        binding.recHourlyForecast.adapter!!.notifyDataSetChanged()
    }

    private fun updateDailyAdapter(list: List<DailyForecastEntity>) {
        val adapter = DailyRecyclerAdapter(args.icon)
        binding.recDailyForecast.adapter = adapter
        binding.recDailyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        (binding.recDailyForecast.adapter as DailyRecyclerAdapter).submitList(list)
        binding.recDailyForecast.adapter!!.notifyDataSetChanged()
    }
}