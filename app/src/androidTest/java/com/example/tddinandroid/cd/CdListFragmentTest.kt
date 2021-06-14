package com.example.tddinandroid.cd

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddinandroid.R
import com.example.tddinandroid.cd.ui.CdListFragment
import com.example.tddinandroid.hilt.launchFragmentInHiltContainer
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CdListFragmentTest {

    @Test
    fun cdTotalCountIsAccessible() = runBlockingTest {
        launchFragmentInHiltContainer<CdListFragment>(
            fragmentArgs = null,
            factory = null
        ) {
            assertEquals(
                "The element count should match!",
                3,
                this.getCount()
            )
        }
    }
}