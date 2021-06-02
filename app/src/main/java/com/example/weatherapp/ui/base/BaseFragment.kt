package com.example.weatherapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 *  Common fragments behaviour contained in the BaseFragment
 */
abstract class BaseFragment<VB : ViewBinding, ViewModel : BaseViewModel, VMFactory : BaseViewModelFactory> :
    Fragment() {

    private lateinit var viewModelFactory: VMFactory
    protected open lateinit var viewModel: ViewModel
    protected open lateinit var binding: VB

    protected abstract fun getViewModelFactory(): VMFactory
    protected abstract fun getViewModelClass(): Class<ViewModel>
    protected abstract fun getViewBinding(): VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initBinding()
        init()
        return binding.root
    }

    private fun initBinding() {
        binding = getViewBinding()
        viewModelFactory = getViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    open fun init() {}

    open fun getArgs() {}
}
