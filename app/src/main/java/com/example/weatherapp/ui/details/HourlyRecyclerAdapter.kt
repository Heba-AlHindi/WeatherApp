package com.example.weatherapp.ui.details

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.database.models.HourlyForecastEntity
import com.example.weatherapp.databinding.ItemHourlyBinding
import com.example.weatherapp.ui.base.BaseRecyclerAdapter
import com.example.weatherapp.utils.getHourOfDay

class HourlyRecyclerAdapter : BaseRecyclerAdapter<HourlyForecastEntity>(hourlyCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemHourlyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(binding: ViewBinding, position: Int) {
        val hourlyBinding = binding as ItemHourlyBinding
        hourlyBinding.rootView.apply {
            radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f,
                context.resources.displayMetrics
            )
        }
        // bind data
        val dt = currentList[position].dt
        hourlyBinding.tvTime.text = getHourOfDay(dt)
        hourlyBinding.tvTemp.text = currentList[position].temp.toString().substringBefore(".") + "°"
    }
}

val hourlyCallback = object : DiffUtil.ItemCallback<HourlyForecastEntity>() {
    override fun areItemsTheSame(
        oldItem: HourlyForecastEntity,
        newItem: HourlyForecastEntity
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: HourlyForecastEntity,
        newItem: HourlyForecastEntity
    ): Boolean {
        return oldItem == newItem
    }
}

