package com.example.weatherapp.ui.splash

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.example.weatherapp.databinding.ActivitySplashBinding
import com.example.weatherapp.ui.base.BaseActivity
import com.example.weatherapp.ui.base.BaseViewModel
import com.example.weatherapp.ui.main.MainActivity


class SplashActivity : BaseActivity<ActivitySplashBinding, BaseViewModel>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)
    override fun getViewModelClass() = BaseViewModel::class.java

    override fun init() {
        super.init()
        hideStatusAndNavigationBar()
    }

    override fun setUpViews() {
        super.setUpViews()
        Handler(Looper.getMainLooper()).postDelayed({
            startNewActivity<MainActivity>(this, true)
        }, 3000)
    }

    private fun hideStatusAndNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(
                android.view.WindowInsets.Type.statusBars() or
                        android.view.WindowInsets.Type.navigationBars()
            )
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            )
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        }
    }
}