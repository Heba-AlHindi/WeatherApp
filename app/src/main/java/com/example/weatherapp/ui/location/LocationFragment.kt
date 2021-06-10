package com.example.weatherapp.ui.location

import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.AppApplication
import com.example.weatherapp.database.SharedPrefHandler.locationsLastFetched
import com.example.weatherapp.database.models.CitiesForecastEntity
import com.example.weatherapp.database.models.CurrentForecastEntity
import com.example.weatherapp.databinding.LocationFragmentBinding
import com.example.weatherapp.network.utils.NetworkStatus
import com.example.weatherapp.network.utils.Resource
import com.example.weatherapp.ui.base.BaseFragment
import java.util.concurrent.TimeUnit

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

    private val prefs: SharedPreferences by lazy {
        AppApplication.prefs!!
    }

    // set data to views
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set last updated

        viewModel.getCitiesForecast().observe(viewLifecycleOwner) {
            when (it.networkStatus) {
                NetworkStatus.LOADING -> {
                    binding.tvLastUpdated.visibility = View.GONE
                    binding.shimmerFrameLayout.startShimmerAnimation()
                }
                NetworkStatus.SUCCESS -> {
                    /** upload new data from network **/
                    setLastUpdate()
                    manageUI(it)
                }
                NetworkStatus.ERROR -> {
                    /** manage all errors including network connectivity by upload cache **/
                    setLastUpdate()
                    manageUI(it)
                }
            }
        }
    }

    private fun setLastUpdate() {
        val res: Resources = resources
        val lastFetched = prefs.locationsLastFetched
        val diff = System.currentTimeMillis() - lastFetched
        val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diff)
        val text: String =
            String.format(res.getString(com.example.weatherapp.R.string.last_updated), diffInMin)
        binding.tvLastUpdated.text = text
        binding.tvLastUpdated.visibility = View.VISIBLE
    }

    private fun manageUI(it: Resource<CitiesForecastEntity>) {
        binding.shimmerFrameLayout.stopShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.recLocation.visibility = View.VISIBLE
        val list = it.data?.list?.subList(0, it.data.list!!.size)
        list?.let { it1 -> updateLocationAdapter(it1) }
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


