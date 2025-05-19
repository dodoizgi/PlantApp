package com.example.plantapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testBottomNavigation() {
        // Ana sayfa kontrolü
        onView(withId(R.id.nav_host_fragment))
            .check(matches(isDisplayed()))

        // Diagnose sekmesine tıklama
        onView(withId(R.id.nav_diagnose))
            .perform(click())

        // Garden sekmesine tıklama
        onView(withId(R.id.nav_garden))
            .perform(click())

        // Profile sekmesine tıklama
        onView(withId(R.id.nav_profile))
            .perform(click())

        // Ana sayfaya geri dönme
        onView(withId(R.id.nav_home))
            .perform(click())
    }
} 