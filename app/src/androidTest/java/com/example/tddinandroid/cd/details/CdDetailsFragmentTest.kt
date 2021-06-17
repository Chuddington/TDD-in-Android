package com.example.tddinandroid.cd.details

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddinandroid.R
import com.example.tddinandroid.cd.details.ui.CdDetailsFragment
import com.example.tddinandroid.cd.ui.CdListFragment
import com.example.tddinandroid.config.activity.HiltActivityFactory
import com.example.tddinandroid.config.fragment.FragmentFactory
import com.example.tddinandroid.hilt.HiltTestActivity
import com.example.tddinandroid.hilt.launchFragmentInContainer
import com.example.tddinandroid.hilt.onFragment
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CdDetailsFragmentTest {

    private val testNavHostController =
        TestNavHostController(ApplicationProvider.getApplicationContext())

    private val fragmentFactory = FragmentFactory(
        navController = testNavHostController,
        klass = CdDetailsFragment::class
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
    fun aCdCanBePurchasedByTheUser() {
        val cdId = 1
        var purchaseSet: (() -> Set<Int>) = { setOf<Int>() }

        scenario = scenario.onFragment<HiltTestActivity, CdDetailsFragment>(
            fragmentFactory.getTagName()
        ) {
            testNavHostController.setGraph(R.navigation.cd_navigation)
            purchaseSet = { it.getPurchases() }
        }

        onView(withId(R.id.cdDetailsPurchaseButton)).perform(click())
        assertTrue(
            "The set of purchased CDs should contain the ID!",
            purchaseSet().contains(cdId)
        )
    }
}