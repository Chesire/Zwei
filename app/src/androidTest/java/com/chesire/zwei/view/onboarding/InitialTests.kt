package com.chesire.zwei.view.onboarding

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.chesire.zwei.MockApplication
import com.chesire.zwei.R
import com.chesire.zwei.dagger.MockInjector
import com.chesire.zwei.dagger.components.DaggerMockComponent
import com.chesire.zwei.dagger.components.MockComponent
import com.chesire.zwei.dagger.espressoDaggerMockRule
import com.chesire.zwei.dagger.modules.ApplicationModule
import com.chesire.zwei.util.PrefHelper
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class InitialTests {
    @get:Rule
    val activityRule = ActivityTestRule(OnboardingActivity::class.java, false, false)

    @Rule
    @JvmField
    var rule = espressoDaggerMockRule()

    @Mock
    private lateinit var prefHelper: PrefHelper

    @Before
    fun setUp() {
        prefHelper.clear()
    }

    @Test
    fun initialApplicationStartLaunchesIntoWelcome() {
        activityRule.launchActivity(null)
        onView(withId(R.id.textWelcome)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun fromWelcomeCanNavigateToRequest() {
        clickOn(R.id.buttonNext)
        onView(withId(R.id.textRequest)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun fromRequestCanNavigateToEnterCharacterFlow() {
        clickOn(R.id.buttonNext)
        clickOn(R.id.buttonNext)
        onView(withId(R.id.editWorld)).check(matches(ViewMatchers.isDisplayed()))
    }
}