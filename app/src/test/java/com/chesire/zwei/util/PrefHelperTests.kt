package com.chesire.zwei.util

import android.content.Context
import android.content.SharedPreferences
import com.chesire.zwei.R
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Ignore
import org.junit.Test

private const val bypassedWelcome = "bypassedWelcome"
private const val bypassedRequest = "bypassedRequest"
private const val acquiredCharacter = "acquiredCharacter"
private const val allowAnalytics = "allowAnalytics"
private const val allowCrashReporting = "allowCrashReporting"

class PrefHelperTests {
    private val mockContext = mockk<Context> {
        every { getString(R.string.key_bypassed_welcome) } returns bypassedWelcome
        every { getString(R.string.key_bypassed_request) } returns bypassedRequest
        every { getString(R.string.key_acquired_character) } returns acquiredCharacter
        every { getString(R.string.key_allow_analytics) } returns allowAnalytics
        every { getString(R.string.key_allow_crash_reporting) } returns allowCrashReporting
    }

    @Test
    fun `hasBypassedWelcome set() changes value in SharedPreferences`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> {
            every { putBoolean(bypassedWelcome, true) } returns this
            every { apply() } just Runs
        }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        with(PrefHelper(mockContext, mockPreferences)) {
            hasBypassedWelcome = true
        }

        verify { mockPrefEditor.putBoolean(bypassedWelcome, true) }
    }

    @Test
    fun `hasBypassedRequest set() changes value in SharedPreferences`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> {
            every { putBoolean(bypassedRequest, true) } returns this
            every { apply() } just Runs
        }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        with(PrefHelper(mockContext, mockPreferences)) {
            hasBypassedRequest = true
        }

        verify { mockPrefEditor.putBoolean(bypassedRequest, true) }
    }

    @Test
    fun `hasAcquiredCharacter set() changes value in SharedPreferences`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> {
            every { putBoolean(acquiredCharacter, true) } returns this
            every { apply() } just Runs
        }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        with(PrefHelper(mockContext, mockPreferences)) {
            hasAcquiredCharacter = true
        }

        verify { mockPrefEditor.putBoolean(acquiredCharacter, true) }
    }

    @Test
    fun `allowAnalytics set() changes value in SharedPreferences`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> {
            every { putBoolean(allowAnalytics, true) } returns this
            every { apply() } just Runs
        }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        with(PrefHelper(mockContext, mockPreferences)) {
            allowAnalytics = true
        }

        verify { mockPrefEditor.putBoolean(allowAnalytics, true) }
    }

    @Test
    fun `allowCrashReporting set() changes value in SharedPreferences`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> {
            every { putBoolean(allowCrashReporting, true) } returns this
            every { apply() } just Runs
        }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        with(PrefHelper(mockContext, mockPreferences)) {
            allowCrashReporting = true
        }

        verify { mockPrefEditor.putBoolean(allowCrashReporting, true) }
    }

    @Test
    fun `calling clear clears shared preferences data`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> {
            every { clear() } returns this
            every { apply() } just Runs
        }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        with(PrefHelper(mockContext, mockPreferences)) {
            clear()
        }

        verify { mockPrefEditor.clear() }
    }

    @Test(expected = IllegalStateException::class)
    @Ignore("Can't test this for now, as using properties stops invalid data being passed in")
    fun `invalid data causes an IllegalStateException`() {
        val mockPrefEditor = mockk<SharedPreferences.Editor> { }
        val mockPreferences = mockk<SharedPreferences> {
            every { edit() } returns mockPrefEditor
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)
    }
}
