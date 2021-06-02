package com.example.weatherapp.ui.details

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.database.models.DailyForecastEntity
import com.example.weatherapp.database.models.HourlyForecastEntity
import com.example.weatherapp.database.models.MainForecastEntity
import com.example.weatherapp.databinding.ActivityDetailsBinding
import com.example.weatherapp.network.utils.NetworkStatus
import com.example.weatherapp.ui.base.BaseActivity
import com.squareup.picasso.Picasso


class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {

    override fun getViewBinding() = ActivityDetailsBinding.inflate(layoutInflater)
    override fun getViewModelClass() = DetailsViewModel::class.java
    private var iconPath: String = ""

    override fun init() {
        super.init()
        initAdapters()
    }

    override fun setUpViews() {
        super.setUpViews()
        /** set data views **/
        val intent = intent.extras?.let { DetailsActivityArgs.fromBundle(it) }
        val lt = intent?.coordination?.lat
        val ln = intent?.coordination?.lon

        binding.tvCity.text = intent?.cityName ?: ""
        binding.tvStatus.text = intent?.main ?: ""
        Picasso.get()
            .load("http://openweathermap.org/img/w/${intent?.icon ?: ""}.png")
            .into(binding.imgForecast)
        val details = intent?.currentDetails
        val speed = intent?.windSpeed
        val data = speed?.let { details?.let { it1 -> mapDetails(it, it1) } }
        if (data != null) {
            updateDetailsAdapter(data)
        }
        iconPath = intent?.icon!!
        viewModel.getCityDetailsForecast(lt!!, ln!!).observe(this) {
            when (it.networkStatus) {
                NetworkStatus.LOADING -> {
                }
                NetworkStatus.SUCCESS -> {
                    val hourlyList = it.data?.hourly?.subList(0, it.data.hourly!!.size)
                    val dailyList = it.data?.daily?.subList(0, it.data.daily!!.size)
                    hourlyList?.let { it1 -> updateHourlyAdapter(it1) }
                    dailyList?.let { it1 -> updateDailyAdapter(it1) }
                }
                NetworkStatus.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
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
        val dailyAdapter = DailyRecyclerAdapter(iconPath)
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
        val adapter = DailyRecyclerAdapter(iconPath)
        binding.recDailyForecast.adapter = adapter
        binding.recDailyForecast.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        (binding.recDailyForecast.adapter as DailyRecyclerAdapter).submitList(list)
        binding.recDailyForecast.adapter!!.notifyDataSetChanged()
    }

    override fun getArgs() {
        super.getArgs()
        intent.extras?.let { DetailsActivityArgs.fromBundle(it).coordination.lat }
    }
}