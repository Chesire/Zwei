package com.chesire.zwei.view.onboarding

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.chesire.zwei.R
import com.chesire.zwei.dagger.espressoDaggerMockRule
import com.chesire.zwei.util.PrefHelper
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class InitialTests {
    @get:Rule
    var daggerRule = espressoDaggerMockRule()

    @get:Rule
    val activityRule = ActivityTestRule(OnboardingActivity::class.java, false, false)

    @Mock
    private lateinit var prefHelper: PrefHelper

    @Test
    fun initialApplicationStartLaunchesIntoWelcome() {
        activityRule.launchActivity(null)
        onView(withId(R.id.textWelcome)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun fromWelcomeCanNavigateToRequest() {
        activityRule.launchActivity(null)
        clickOn(R.id.buttonNext)
        onView(withId(R.id.textRequest)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun fromRequestCanNavigateToEnterCharacterFlow() {
        activityRule.launchActivity(null)
        clickOn(R.id.buttonNext)
        clickOn(R.id.buttonNext)
        onView(withId(R.id.editWorld)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun ifWelcomeIsDoneStartInRequest() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        activityRule.launchActivity(null)
        onView(withId(R.id.textRequest)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun ifRequestIsDoneStartInEnterCharacterFlow() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        `when`(prefHelper.hasBypassedRequest).thenReturn(true)
        activityRule.launchActivity(null)
        onView(withId(R.id.editWorld)).check(matches(ViewMatchers.isDisplayed()))
    }
}