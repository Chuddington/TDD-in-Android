package com.example.tddinandroid.hilt

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import com.example.tddinandroid.config.activity.ActivityFactory
import com.example.tddinandroid.config.fragment.FragmentFactory

fun <A : AppCompatActivity> launchFragmentInContainer(
    activityFactory: ActivityFactory<A>,
    fragmentFactory: FragmentFactory,
): ActivityScenario<A> {
    return activityFactory.create().onActivity { activity ->
        val fragment = fragmentFactory.generateFragment(activity)
        activity.commitFragmentNow(fragment, tag = fragmentFactory.klass.java.name)
    }
}
