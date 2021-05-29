package com.example.weatherapp.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 *  Common activities behaviour contained in the BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}