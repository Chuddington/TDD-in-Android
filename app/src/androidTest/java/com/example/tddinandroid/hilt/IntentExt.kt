package com.example.tddinandroid.hilt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario

fun <A : AppCompatActivity> Intent.launchActivityScenario(): ActivityScenario<A> =
    ActivityScenario.launch(this)