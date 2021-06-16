package com.example.tddinandroid.config.activity

import android.content.ComponentName
import android.content.Intent
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.tddinandroid.hilt.HiltTestActivity
import com.example.tddinandroid.hilt.launchActivityScenario
import com.example.tddinandroid.hilt.withFragmentFactory
import com.example.tddinandroid.R

data class ThemeBasedActivityFactory<A : AppCompatActivity>(
    private val componentName: ComponentName,
    @StyleRes private val themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    private val fragmentFactory: FragmentFactory? = null
) : ActivityFactory<A> {

    override fun create(): ActivityScenario<A> =
        Intent.makeMainActivity(componentName)
            .putExtra(FRAGMENT_SCENARIO_THEME_EXTRAS_BUNDLE_KEY, themeResId)
            .launchActivityScenario<A>()
            .withFragmentFactory(fragmentFactory)

    companion object {
        private const val FRAGMENT_SCENARIO_THEME_EXTRAS_BUNDLE_KEY =
            "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"
    }
}

val HiltActivityFactory = ThemeBasedActivityFactory<HiltTestActivity>(
    componentName = ComponentName(
        ApplicationProvider.getApplicationContext(),
        HiltTestActivity::class.java
    )
)