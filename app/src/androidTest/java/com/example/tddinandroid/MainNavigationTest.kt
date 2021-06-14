package com.example.tddinandroid

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainNavigationTest {

    @Test
    fun something() {
        launchActivity<MainActivity>().use {
            onView(withContentDescription("Open navigation drawer")).perform(ViewActions.click())
            onView(withId(R.id.nav_cd_list)).perform(ViewActions.click())
            onView(withId(R.id.cdListLayout)).check(ViewAssertions.matches(isDisplayed()))
        }
    }
}