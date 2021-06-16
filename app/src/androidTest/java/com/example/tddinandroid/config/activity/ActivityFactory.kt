package com.example.tddinandroid.config.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario

interface ActivityFactory<A : AppCompatActivity> : Factory<ActivityScenario<A>> {
}