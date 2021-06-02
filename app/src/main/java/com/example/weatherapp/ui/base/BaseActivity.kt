package com.example.weatherapp.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *  Common activities behaviour contained in the BaseActivity
 */
abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected open lateinit var viewModel: VM
    protected open lateinit var binding: VB

    protected abstract fun getViewBinding(): VB
    protected abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        init()
        setContentView(binding.root)
        setUpViews()
    }

    // start new activity using Intent
    protected inline fun <reified T : Activity> startNewActivity(
        context: Context,
        finish: Boolean
    ) {
        startActivity(Intent(context, T::class.java))
        if (finish) finish()
    }

    // add new Fragment
    protected fun addFragment(
        fragmentContainerId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        supportFragmentManager.beginTransaction().let { fragmentTransaction ->
            fragmentTransaction.add(fragmentContainerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag)
            }
            fragmentTransaction.commit()
        }
    }

    // replace Fragment
    protected fun replaceFragment(
        fragmentContainerId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        supportFragmentManager.beginTransaction().let { fragmentTransaction ->
            fragmentTransaction.replace(fragmentContainerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag)
            }
            fragmentTransaction.commit()
        }
    }

    private fun initBinding() {
        binding = getViewBinding()
        viewModel = ViewModelProvider(this).get(getViewModelClass())
    }
    open fun setUpViews() {}

    open fun init() {}

    open fun getArgs() {

    }
}