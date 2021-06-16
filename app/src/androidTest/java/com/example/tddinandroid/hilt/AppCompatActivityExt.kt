package com.example.tddinandroid.hilt

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlin.reflect.KClass

fun AppCompatActivity.instantiateFragment(
    classLoader: ClassLoader,
    tag: String
): Fragment = supportFragmentManager.instantiateFragment(classLoader, tag)

fun AppCompatActivity.findFragment(tag: String): Fragment? =
    supportFragmentManager.findFragmentByTag(tag)

fun AppCompatActivity.commitFragmentNow(
    fragment: Fragment,
    @IdRes containerViewId: Int = android.R.id.content,
    tag: String = ""
) {
    supportFragmentManager.beginTransaction()
        .add(containerViewId, fragment, tag)
        .commitNow()
}

fun AppCompatActivity.withFragmentFactory(
    fragmentFactory: FragmentFactory?
): AppCompatActivity = apply {
    fragmentFactory?.let {
        supportFragmentManager.fragmentFactory = it
    }
}