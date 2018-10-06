package com.chesire.zwei.view

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.chesire.zwei.dagger.espressoDaggerMockRule
import com.chesire.zwei.util.PrefHelper
import com.chesire.zwei.view.onboarding.OnboardingActivity
import com.chesire.zwei.view.profile.ProfileActivity
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
    fun startInOnboardingFlow() {
        `when`(prefHelper.shouldDisplayOnboarding).thenReturn(true)

        activityRule.launchActivity(null)

        intended(hasComponent(OnboardingActivity::class.java.name))
    }

    @Test
    fun ifOnboardingCompleteStartInMainActivity() {
        `when`(prefHelper.shouldDisplayOnboarding).thenReturn(false)

        activityRule.launchActivity(null)

        intended(hasComponent(ProfileActivity::class.java.name))
    }
}
