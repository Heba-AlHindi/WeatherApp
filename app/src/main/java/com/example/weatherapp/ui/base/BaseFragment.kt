package com.example.weatherapp.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 *  Common fragments behaviour contained in the BaseFragment
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}