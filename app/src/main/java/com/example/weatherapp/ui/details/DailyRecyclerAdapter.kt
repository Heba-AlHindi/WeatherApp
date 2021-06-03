package com.example.weatherapp.ui.details

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.database.models.DailyForecastEntity
import com.example.weatherapp.databinding.ItemDailyBinding
import com.example.weatherapp.ui.base.BaseRecyclerAdapter
import com.example.weatherapp.utils.getDay
import com.squareup.picasso.Picasso

class DailyRecyclerAdapter(private val iconPath: String) :
    BaseRecyclerAdapter<DailyForecastEntity>(dailyCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemDailyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(binding: ViewBinding, position: Int) {
        val dailyBinding = binding as ItemDailyBinding
        dailyBinding.rootView.apply {
            radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f,
                context.resources.displayMetrics
            )
        }
        // bind data
        dailyBinding.tvTemp.text =
            currentList[position].temp?.day.toString().substringBefore(".") + "°"
//        val iconPath = currentList[position].weather?.get(0)?.icon
        Picasso.get()
            .load("http://openweathermap.org/img/w/$iconPath.png")
            .into(dailyBinding.imgForecast)
        // bind time
        val dt = currentList[position].dt
        binding.tvDay.text = getDay(dt)

    }
}

val dailyCallback = object : DiffUtil.ItemCallback<DailyForecastEntity>() {
    override fun areItemsTheSame(
        oldItem: DailyForecastEntity,
        newItem: DailyForecastEntity
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DailyForecastEntity,
        newItem: DailyForecastEntity
    ): Boolean {
        return oldItem == newItem
    }
}