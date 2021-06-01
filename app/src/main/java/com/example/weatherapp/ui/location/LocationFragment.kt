package com.example.weatherapp.ui.location

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.database.models.cityCurrentForecast.CurrentForecastEntity
import com.example.weatherapp.databinding.LocationFragmentBinding
import com.example.weatherapp.network.utils.NetworkStatus
import com.example.weatherapp.ui.base.BaseFragment

/**
 *  LocationFragment Contains Required Locations With its currentForecast
 */
class LocationFragment : BaseFragment<LocationFragmentBinding, LocationViewModel>() {

    override fun getViewBinding() = LocationFragmentBinding.inflate(layoutInflater)

    override fun getViewModelClass() = LocationViewModel::class.java

    override fun init() {
        super.init()
        initForecastAdapter()
    }

    // set data to views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCitiesForecast()?.observe(viewLifecycleOwner) {
            when (it.networkStatus) {
                NetworkStatus.LOADING -> {
                    Toast.makeText(context, "Loading ...", Toast.LENGTH_LONG).show()
                }
                NetworkStatus.SUCCESS -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    it.data?.citiesCurrentForecast?.toList()
                        ?.let { it1 -> updateForecastAdapter(it1) }
                }
                NetworkStatus.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initForecastAdapter() {
        val adapter = LocationRecyclerAdapter { item ->
            findNavController().navigate(R.id.action_location_to_home)
        }
        binding.recLocation.adapter = adapter
        binding.recLocation.layoutManager = GridLayoutManager(context, 2)
        binding.recLocation.setHasFixedSize(true)
        postponeEnterTransition()
        binding.recLocation.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun updateForecastAdapter(list: List<CurrentForecastEntity>) {
        (binding.recLocation.adapter as LocationRecyclerAdapter).submitList(list)
    }

}