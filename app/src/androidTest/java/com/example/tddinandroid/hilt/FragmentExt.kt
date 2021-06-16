package com.example.tddinandroid.hilt

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

fun Fragment.withArguments(arguments: Bundle?) = apply {
    this.arguments = arguments
}

fun Fragment.withNavController(navController: NavController?) = apply {
    viewLifecycleOwnerLiveData.observeForever { lifecycleOwner ->
        if (lifecycleOwner != null) {
            navController?.let { controller ->
                Navigation.setViewNavController(requireView(), controller)
            }
        }
    }
}