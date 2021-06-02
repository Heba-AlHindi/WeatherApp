package com.example.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.DetailsFragmentBinding
import com.example.weatherapp.ui.base.BaseFragment
import com.squareup.picasso.Picasso

/**
 *  HomeFragment Contains Current && Hourly Forecast
 */
class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>() {

    override fun getViewBinding() = DetailsFragmentBinding.inflate(layoutInflater)

    override fun getViewModelClass() = DetailsViewModel::class.java

    override fun init() {
        super.init()
        initDetailsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get args from bundle
        val bundle = arguments
        if (bundle != null) {
            val args = DetailsFragmentArgs.fromBundle(bundle)
            // set data views
            binding.tvCity.text = args.cityName
            binding.tvStatus.text = args.main
            Picasso.get()
                .load("http://openweathermap.org/img/w/${args.icon}.png")
                .into(binding.imgForecast)

            // make map for weather details info
            val map = mutableMapOf<String, String?>()
            map["Temp"] = args.currentDetails?.temp.toString().substringBefore(".") + "°"
            args.currentDetails?.feels_like.also { map["Feels like"] = it.toString().substringBefore(".") + "°" }
            map["Wind"] = args.windSpeed.toString().substringBefore(".") + " meter/sec"
            args.currentDetails?.pressure.also { map["Pressure"] = it.toString().substringBefore(".") }
            args.currentDetails?.humidity?.toDouble().also { map["Humidity"] = it.toString().substringBefore(".") + "%" }
            val data: ArrayList<Pair<String, String>> = arrayListOf()
            map.forEach { (t, v) -> data.add(Pair(t, v.toString())) }
            updateDetailsAdapter(data)
        }
    }

    private fun initDetailsAdapter() {
        val adapter = DetailsRecyclerAdapter()
        binding.recForecastData.adapter = adapter
        binding.recForecastData.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        postponeEnterTransition()
        binding.recForecastData.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun updateDetailsAdapter(map: ArrayList<Pair<String, String>>) {
        val adapter = DetailsRecyclerAdapter()
        binding.recForecastData.adapter = adapter
        binding.recForecastData.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        (binding.recForecastData.adapter as DetailsRecyclerAdapter).submitList(map)
        binding.recForecastData.adapter!!.notifyDataSetChanged()

    }
}