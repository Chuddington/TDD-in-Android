package com.example.tddinandroid.cd

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddinandroid.cd.ui.CdListFragment
import com.example.tddinandroid.hilt.launchFragmentInHiltContainer
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CdListFragmentTest {

    @Test
    fun cdTotalCountIsAccessible() = runBlockingTest {
        val scenario = launchFragmentInHiltContainer<CdListFragment>(
            fragmentArgs = null,
            factory = null
        ) {
            assertEquals(
                "The element count should match!",
                3,
                this.getCount()
            )
        }

        scenario.close()
    }
}