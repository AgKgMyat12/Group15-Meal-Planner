package com.example.hp.mealplanner.fragments

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            readArguments(bundle)
        }

        if (savedInstanceState != null) {
            readArguments(savedInstanceState)
        }
    }

    protected fun readArguments(bundle: Bundle) {

    }
}