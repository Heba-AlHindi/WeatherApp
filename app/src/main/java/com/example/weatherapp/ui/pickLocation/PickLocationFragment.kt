package com.example.weatherapp.ui.pickLocation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.ui.base.BaseFragment

class PickLocationFragment : BaseFragment() {

    companion object {
        fun newInstance() = PickLocationFragment()
    }

    private lateinit var viewModel: PickLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pick_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PickLocationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}