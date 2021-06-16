package com.example.tddinandroid.hilt

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario

inline fun <A : AppCompatActivity, reified F : Fragment> ActivityScenario<A>.onFragment(
    fragmentTag: String,
    crossinline action: (F) -> Unit
): ActivityScenario<A> = onActivity { activity ->
    val fragment = activity.findFragment(fragmentTag) as F
    action(fragment)
}

fun <A : AppCompatActivity> ActivityScenario<A>.withFragmentFactory(
    factory: FragmentFactory?
): ActivityScenario<A> = onActivity { it.withFragmentFactory(factory) }