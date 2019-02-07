package com.chesire.zwei.view.onboarding.initial.request

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chesire.zwei.util.PrefHelper
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RequestViewModelTests {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `saveAnalyticsChoices stores allowAnalytics value in sharedPref`() {
        val slot = CapturingSlot<Boolean>()
        val mockPreferences = mockk<PrefHelper> {
            every { allowAnalytics = capture(slot) } just Runs
            every { allowCrashReporting = any() } just Runs
        }

        with(RequestViewModel(mockPreferences)) {
            analyticsEnabled.value = true
            saveAnalyticsChoices()
        }

        assertTrue(slot.isCaptured)
        assertTrue(slot.captured)
    }

    @Test
    fun `saveAnalyticsChoices stores allowCrashReporting value in sharedPref`() {
        val slot = CapturingSlot<Boolean>()
        val mockPreferences = mockk<PrefHelper> {
            every { allowAnalytics = any() } just Runs
            every { allowCrashReporting = capture(slot) } just Runs
        }

        with(RequestViewModel(mockPreferences)) {
            crashReportingEnabled.value = true
            saveAnalyticsChoices()
        }

        assertTrue(slot.isCaptured)
        assertTrue(slot.captured)
    }

    @Test
    fun `saveAnalyticsChoices with null analyticsEnabled stores false in sharedPref`() {
        val slot = CapturingSlot<Boolean>()
        val mockPreferences = mockk<PrefHelper> {
            every { allowAnalytics = capture(slot) } just Runs
            every { allowCrashReporting = any() } just Runs
        }

        with(RequestViewModel(mockPreferences)) {
            analyticsEnabled.value = null
            assertNull(analyticsEnabled.value)
            saveAnalyticsChoices()
        }

        assertFalse(slot.captured)
    }

    @Test
    fun `saveAnalyticsChoices with null allowCrashReporting stores false in sharedPref`() {
        val slot = CapturingSlot<Boolean>()
        val mockPreferences = mockk<PrefHelper> {
            every { allowAnalytics = any() } just Runs
            every { allowCrashReporting = capture(slot) } just Runs
        }

        with(RequestViewModel(mockPreferences)) {
            crashReportingEnabled.value = null
            assertNull(crashReportingEnabled.value)
            saveAnalyticsChoices()
        }

        assertFalse(slot.captured)
    }
}
