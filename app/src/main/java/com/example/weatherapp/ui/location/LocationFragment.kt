package com.example.weatherapp.ui.location

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.database.models.CurrentForecastEntity
import com.example.weatherapp.databinding.LocationFragmentBinding
import com.example.weatherapp.network.utils.NetworkStatus
import com.example.weatherapp.ui.base.BaseFragment

/**
 *  LocationFragment Contains Required Locations With its currentForecast
 */
class LocationFragment :
    BaseFragment<LocationFragmentBinding, LocationViewModel, LocationViewModelFactory>() {

    override fun getViewModelFactory() = LocationViewModelFactory()

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
                item.name?.let { it1 ->
                    item.sys?.timezone?.let { it2 ->
                        item.weather?.get(0)?.icon?.let { it3 ->
                            item.main?.let { it4 ->
                                item.weather?.get(0)?.main?.let { it5 ->
                                    item.coord?.let { it6 ->
                                        LocationFragmentDirections.actionLocationToDetailsActivity(
                                            it1,
                                            it2,
                                            it3,
                                            it6,
                                            it4,
                                            it,
                                            it5
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
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
                item.name?.let { it1 ->
                    item.sys?.timezone?.let { it2 ->
                        item.main?.let { it3 ->
                            item.coord?.let { it4 ->
                                item.weather?.get(0)?.icon?.let { it5 ->
                                    item.weather?.get(0)?.main?.let { it6 ->
                                        LocationFragmentDirections.actionLocationToDetailsActivity(
                                            it1,
                                            it2,
                                            it5,
                                            it4,
                                            it3,
                                            it,
                                            it6
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
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


