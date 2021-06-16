package com.example.tddinandroid.config.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.tddinandroid.hilt.instantiateFragment
import com.example.tddinandroid.hilt.withArguments
import com.example.tddinandroid.hilt.withNavController
import kotlin.reflect.KClass

data class FragmentFactory(
    private val arguments: Bundle? = null,
    private val navController: NavController? = null,
    val klass: KClass<out Fragment>
) {
    fun generateFragment(activity: AppCompatActivity): Fragment {
        return activity.instantiateFragment(getClassLoader(), getTagName())
            .withArguments(arguments)
            .withNavController(navController)
    }

    private fun getClassLoader(): ClassLoader = klass.java.classLoader!!

    fun getTagName(): String = klass.java.name
}