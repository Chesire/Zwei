package com.chesire.zwei.view.onboarding

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.chesire.zwei.R
import com.chesire.zwei.dagger.espressoDaggerMockRule
import com.chesire.zwei.util.PrefHelper
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
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
        assertDisplayed(R.id.textWelcome)
    }

    @Test
    fun fromWelcomeCanNavigateToRequest() {
        activityRule.launchActivity(null)
        clickOn(R.id.buttonNext)
        assertDisplayed(R.id.textRequest)
    }

    @Test
    fun fromRequestCanNavigateToEnterCharacterFlow() {
        activityRule.launchActivity(null)
        clickOn(R.id.buttonNext)
        clickOn(R.id.buttonNext)
        assertDisplayed(R.id.editWorld)
    }

    @Test
    fun ifWelcomeIsDoneStartInRequest() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        activityRule.launchActivity(null)
        assertDisplayed(R.id.textRequest)
    }

    @Test
    fun ifRequestIsDoneStartInEnterCharacterFlow() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        `when`(prefHelper.hasBypassedRequest).thenReturn(true)
        activityRule.launchActivity(null)
        assertDisplayed(R.id.editWorld)
    }
}
