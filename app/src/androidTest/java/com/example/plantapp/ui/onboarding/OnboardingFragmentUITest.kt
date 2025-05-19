package com.example.plantapp.ui.onboarding

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plantapp.MainActivity
import com.example.plantapp.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingFragmentUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        // SharedPreferences'ı temizle
        activityRule.scenario.onActivity { activity ->
            activity.getSharedPreferences("prefs", android.content.Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
        }
    }

    @Test
    fun testOnboardingFlow() {
        // İlk sayfanın görünürlüğünü kontrol et
        onView(withId(R.id.viewpager_onboarding))
            .check(matches(isDisplayed()))

        // İlk sayfadaki başlığı kontrol et
        onView(withText("Welcome to PlantApp"))
            .check(matches(isDisplayed()))

        // İlk sayfadaki açıklamayı kontrol et
        onView(withText("Identify more than 3000+ plants and 88% accuracy."))
            .check(matches(isDisplayed()))

        // İkinci sayfaya geç
        onView(withId(R.id.viewpager_onboarding))
            .perform(swipeLeft())

        // İkinci sayfadaki başlığı kontrol et
        onView(withText("Take a photo to identify the plant!"))
            .check(matches(isDisplayed()))

        // Üçüncü sayfaya geç
        onView(withId(R.id.viewpager_onboarding))
            .perform(swipeLeft())

        // Üçüncü sayfadaki başlığı kontrol et
        onView(withText("Get plant care guides"))
            .check(matches(isDisplayed()))
    }
} 