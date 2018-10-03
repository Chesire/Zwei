package com.chesire.zwei.view.onboarding

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.chesire.zwei.dagger.espressoDaggerMockRule
import com.chesire.zwei.util.PrefHelper
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class InitialTests {
    @get:Rule
    var daggerRule = espressoDaggerMockRule()

    @get:Rule
    val activityRule = ActivityTestRule(OnboardingActivity::class.java, false, false)

    @Mock
    private lateinit var prefHelper: PrefHelper

}