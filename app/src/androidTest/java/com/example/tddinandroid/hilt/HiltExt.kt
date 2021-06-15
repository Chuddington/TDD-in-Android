package com.example.tddinandroid.hilt

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.tddinandroid.R

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    factory: FragmentFactory? = null,
    navController: NavController? = null,
    crossinline action: T.() -> Unit = {}
): ActivityScenario<HiltTestActivity> {
    val mainActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    ).putExtra(FRAGMENT_SCENARIO_THEME_EXTRAS_BUNDLE_KEY, themeResId)

    return ActivityScenario.launch<HiltTestActivity>(mainActivityIntent).onActivity { activity ->
        factory?.let { factory ->
            activity.supportFragmentManager.fragmentFactory = factory
        }
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            requireNotNull(T::class.java.classLoader) {
                "Classloader for ${T::class.java.simpleName} cannot be null!"
            },
            T::class.java.name
        )
        fragment.arguments = fragmentArgs
        fragment.viewLifecycleOwnerLiveData.observeForever { lifecycleOwner ->
            if (lifecycleOwner != null) {
                navController?.let { controller ->
                    Navigation.setViewNavController(fragment.requireView(), controller)
                }
            }
        }

        activity.supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        (fragment as T).action()
    }
}

const val FRAGMENT_SCENARIO_THEME_EXTRAS_BUNDLE_KEY =
    "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"