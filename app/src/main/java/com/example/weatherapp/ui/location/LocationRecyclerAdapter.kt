package com.example.weatherapp.ui.location

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.weatherapp.database.models.cityCurrentForecast.CurrentForecastEntity
import com.example.weatherapp.databinding.ItemLocationBinding
import com.example.weatherapp.ui.base.BaseRecyclerAdapter
import com.squareup.picasso.Picasso

class LocationRecyclerAdapter(private val callBack: (CurrentForecastEntity) -> Unit) :
    BaseRecyclerAdapter<CurrentForecastEntity>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        val binding = ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        // handle item click using callback
        binding.rootView.setOnClickListener {
            callBack.invoke(getItem(viewType))
        }
        return binding
    }

    override fun bind(binding: ViewBinding, position: Int) {
        val cityBinding = binding as ItemLocationBinding
        cityBinding.rootView.apply {
            radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f,
                context.resources.displayMetrics
            )
        }
        cityBinding.tvTemp.text = currentList[position]?.main?.temp.toString().substringBefore(".") + "°"
        cityBinding.tvStatus.text = currentList[position]?.weather?.get(0)?.main
        cityBinding.tvCityName.text = currentList[position]?.name
        // bind image from network
        val iconPath = currentList[position].weather?.get(0)?.icon
        if (iconPath?.isNotEmpty() == true) {
            Picasso.get()
                .load("http://openweathermap.org/img/w/$iconPath.png")
                .into(binding.imgIcon)
        }
    }
}

val diffCallback = object : DiffUtil.ItemCallback<CurrentForecastEntity>() {
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: CurrentForecastEntity,
        newItem: CurrentForecastEntity
    ): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(
        oldItem: CurrentForecastEntity,
        newItem: CurrentForecastEntity
    ): Boolean =
        oldItem.id == newItem.id
}