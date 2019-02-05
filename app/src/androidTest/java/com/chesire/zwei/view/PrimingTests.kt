package com.chesire.zwei.view

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.chesire.zwei.R
import com.chesire.zwei.dagger.espressoDaggerMockRule
import com.chesire.zwei.util.PrefHelper
import com.chesire.zwei.view.onboarding.OnboardingActivity
import com.chesire.zwei.view.profile.ProfileActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class PrimingTests {
    @get:Rule
    var daggerRule = espressoDaggerMockRule()

    @get:Rule
    val activityRule = ActivityTestRule(PrimingActivity::class.java, false, false)

    @Mock
    private lateinit var prefHelper: PrefHelper

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun startOnboardingFlowInWelcomeIfNotDoneWelcome() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(false)

        activityRule.launchActivity(null)

        intended(hasComponent(OnboardingActivity::class.java.name))
        assertDisplayed(R.id.textWelcome)
    }

    @Test
    fun startOnboardingFlowInRequestIfNotDoneRequest() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        `when`(prefHelper.hasBypassedRequest).thenReturn(false)

        activityRule.launchActivity(null)

        intended(hasComponent(OnboardingActivity::class.java.name))
        assertDisplayed(R.id.fragmentRequestTitle)
    }

    @Test
    fun startOnboardingFlowPastInitialIfCompletedWelcomeAndRequest() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        `when`(prefHelper.hasBypassedRequest).thenReturn(true)

        activityRule.launchActivity(null)

        intended(hasComponent(OnboardingActivity::class.java.name))
        assertDisplayed(R.id.editWorld)
    }

    @Test
    fun ifOnboardingCompleteStartInMainActivity() {
        `when`(prefHelper.hasBypassedWelcome).thenReturn(true)
        `when`(prefHelper.hasBypassedRequest).thenReturn(true)
        `when`(prefHelper.hasAcquiredCharacter).thenReturn(true)

        activityRule.launchActivity(null)

        intended(hasComponent(ProfileActivity::class.java.name))
    }
}
