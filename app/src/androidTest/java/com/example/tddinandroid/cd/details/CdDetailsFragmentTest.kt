package com.example.tddinandroid.cd.details

import android.os.Bundle
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tddinandroid.R
import com.example.tddinandroid.cd.details.ui.CdDetailsFragment
import com.example.tddinandroid.cd.domain.Cd
import com.example.tddinandroid.cd.hilt.CdRepositoryMap
import com.example.tddinandroid.cd.hilt.CdRepositoryModule
import com.example.tddinandroid.cd.hilt.InMemoryMapModule
import com.example.tddinandroid.cd.hilt.PurchasedCdSet
import com.example.tddinandroid.config.activity.HiltActivityFactory
import com.example.tddinandroid.config.fragment.FragmentFactory
import com.example.tddinandroid.hilt.HiltTestActivity
import com.example.tddinandroid.hilt.launchFragmentInContainer
import com.example.tddinandroid.hilt.onFragment
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@UninstallModules(InMemoryMapModule::class)
class CdDetailsFragmentTest {

    private val cdIdKey = InstrumentationRegistry.getInstrumentation()
        .targetContext.resources.getString(R.string.cd_id_key)

    private val navigationArguments = Bundle().apply {
        putInt(cdIdKey, 1)
    }

    private val testNavHostController =
        TestNavHostController(ApplicationProvider.getApplicationContext())

    private val fragmentFactory = FragmentFactory(
        arguments = navigationArguments,
        navController = testNavHostController,
        klass = CdDetailsFragment::class
    )

    private lateinit var scenario: ActivityScenario<HiltTestActivity>

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @CdRepositoryMap
    @JvmField
    val availableCds: MutableMap<Int, Cd> = mutableMapOf(
        0 to Cd(albumName = "Dissolution", artistName = "The Overmind"),
        1 to Cd(albumName = "Wishes and Delusions", artistName = "Rakoon"),
    )

    @BindValue
    @PurchasedCdSet
    @JvmField
    val purchasedCds: MutableSet<Int> = mutableSetOf(0)

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(
            activityFactory = HiltActivityFactory,
            fragmentFactory = fragmentFactory
        )
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun aCdCanBePurchasedByTheUser() = runBlockingTest {
        val cdId = 1

        scenario = scenario.onFragment<HiltTestActivity, CdDetailsFragment>(
            fragmentFactory.getTagName()
        ) {
            testNavHostController.setGraph(R.navigation.cd_navigation)
            testNavHostController.setCurrentDestination(R.id.cdDetailsFragment, navigationArguments)
        }

        onView(withId(R.id.cdDetailsPurchaseButton)).perform(click())

        assertTrue(
            "The set of purchased CDs should contain the ID!",
            purchasedCds.contains(cdId)
        )
    }
}