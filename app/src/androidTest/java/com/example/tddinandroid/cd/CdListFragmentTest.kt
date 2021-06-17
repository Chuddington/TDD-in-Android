package com.example.tddinandroid.cd

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddinandroid.R
import com.example.tddinandroid.cd.ui.CdListFragment
import com.example.tddinandroid.cd.ui.CdViewAdapter
import com.example.tddinandroid.config.activity.HiltActivityFactory
import com.example.tddinandroid.config.fragment.FragmentFactory
import com.example.tddinandroid.hilt.HiltTestActivity
import com.example.tddinandroid.hilt.launchFragmentInContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import com.example.tddinandroid.hilt.onFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CdListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val testNavHostController =
        TestNavHostController(ApplicationProvider.getApplicationContext())

    private val fragmentFactory = FragmentFactory(
        navController = testNavHostController,
        klass = CdListFragment::class
    )

    private lateinit var scenario: ActivityScenario<HiltTestActivity>

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

    @Test
    fun cdTotalCountIsAccessible() = runBlockingTest {
        scenario = scenario.onFragment<HiltTestActivity, CdListFragment>(
            fragmentFactory.getTagName()
        ) {
            assertEquals(
                "The element count should match!",
                3,
                it.getCount()
            )
        }
    }

    @Test
    fun clickingAnItemTakesYouToCdDetailsPage() = runBlockingTest {
        scenario = scenario.onFragment<HiltTestActivity, CdListFragment>(
            fragmentFactory.getTagName()
        ) {
            testNavHostController.setGraph(R.navigation.cd_navigation)
        }

        onView(withId(R.id.cdRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CdViewAdapter.ViewHolder>(
                1,
                ViewActions.click()
            )
        )

        assertEquals(testNavHostController.currentDestination?.id, R.id.cdDetailsFragment)
    }
}