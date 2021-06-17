package com.example.tddinandroid

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun navigatesToCdListFragmentFromActivity() {
        launchActivity<MainActivity>().use {
            onView(withContentDescription("Open navigation drawer")).perform(ViewActions.click())
            onView(withId(R.id.cd_list_navigation)).perform(ViewActions.click())
            onView(withId(R.id.cdListLayout)).check(ViewAssertions.matches(isDisplayed()))
        }
    }
}