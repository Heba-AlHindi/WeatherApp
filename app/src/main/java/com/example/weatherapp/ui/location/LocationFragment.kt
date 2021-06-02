package com.example.weatherapp.ui.location

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
        initLocationAdapter()
    }

    // set data to views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCitiesForecast().observe(viewLifecycleOwner) {
            when (it.networkStatus) {
                NetworkStatus.LOADING -> {
                    binding.shimmerFrameLayout.startShimmerAnimation()
                }
                NetworkStatus.SUCCESS -> {
                    binding.shimmerFrameLayout.stopShimmerAnimation()
                    binding.shimmerFrameLayout.visibility = View.GONE
                    binding.recLocation.visibility = View.VISIBLE
                    val list = it.data?.list?.subList(0, it.data.list!!.size)
                    list?.let { it1 -> updateLocationAdapter(it1) }
                }
                NetworkStatus.ERROR -> {
                    binding.shimmerFrameLayout.stopShimmerAnimation()
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initLocationAdapter() {
        val adapter = LocationRecyclerAdapter { item ->
            val directions = item.wind?.speed?.toFloat()?.let {
                LocationFragmentDirections.actionLocationToHome(
                    item.name,
                    item.sys?.timezone,
                    item.weather?.get(0)?.icon,
                    item.coord,
                    item.main,
                    it,
                    item.weather?.get(0)?.main
                )
            }
            directions?.let { findNavController().navigate(it) }
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

    private fun updateLocationAdapter(list: List<CurrentForecastEntity>) {
        val adapter = LocationRecyclerAdapter { item ->
            val directions = item.wind?.speed?.toFloat()?.let {
                LocationFragmentDirections.actionLocationToHome(
                    item.name,
                    item.sys?.timezone,
                    item.weather?.get(0)?.icon,
                    item.coord,
                    item.main,
                    it,
                    item.weather?.get(0)?.main
                )
            }
            directions?.let { findNavController().navigate(it) }
        }
        binding.recLocation.adapter = adapter
        binding.recLocation.layoutManager = GridLayoutManager(context, 2)
        binding.recLocation.setHasFixedSize(true)
        (binding.recLocation.adapter as LocationRecyclerAdapter).submitList(list)
        binding.recLocation.adapter!!.notifyDataSetChanged()
    }

    override fun onPause() {
        binding.shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}


