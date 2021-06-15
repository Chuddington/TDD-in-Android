package com.example.tddinandroid.cd

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddinandroid.R
import com.example.tddinandroid.cd.ui.CdListFragment
import com.example.tddinandroid.cd.ui.CdViewAdapter
import com.example.tddinandroid.hilt.HiltTestActivity
import com.example.tddinandroid.hilt.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CdListFragmentTest {

    private var scenario: ActivityScenario<HiltTestActivity>? = null

    @After
    fun tearDown() {
        scenario?.close()
    }

    @Test
    fun cdTotalCountIsAccessible() = runBlockingTest {
        val testNavController = TestNavHostController(ApplicationProvider.getApplicationContext())
        scenario =
            launchFragmentInHiltContainer<CdListFragment>(navController = testNavController) {
                assertEquals(
                    "The element count should match!",
                    3,
                    this.getCount()
                )
            }
    }

    @Test
    fun clickingAnItemTakesYouToCdDetailsPage() = runBlockingTest {
        val testNavHostController =
            TestNavHostController(ApplicationProvider.getApplicationContext())
        scenario =
            launchFragmentInHiltContainer<CdListFragment>(navController = testNavHostController) {
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