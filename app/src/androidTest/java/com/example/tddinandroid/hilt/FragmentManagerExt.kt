package com.example.tddinandroid.hilt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.instantiateFragment(
    classLoader: ClassLoader,
    tag: String
): Fragment = fragmentFactory.instantiate(
    classLoader,
    tag
)