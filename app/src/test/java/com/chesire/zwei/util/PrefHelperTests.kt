package com.chesire.zwei.util

import android.content.Context
import android.content.SharedPreferences
import com.chesire.zwei.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

private const val bypassedWelcome = "bypassedWelcome"
private const val bypassedRequest = "bypassedRequest"

class PrefHelperTests {
    private val mockContext = mock<Context> {
        on {
            getString(R.string.key_bypassed_welcome)
        }.thenReturn(bypassedWelcome)
        on {
            getString(R.string.key_bypassed_request)
        }.thenReturn(bypassedRequest)
    }

    @Test
    fun `hasBypassedWelcome set() changes value in SharedPreferences`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)
        classUnderTest.hasBypassedWelcome = true

        verify(mockPrefEditor).putBoolean(bypassedWelcome, true)
    }

    @Test
    fun `hasBypassedRequest set() changes value in SharedPreferences`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)
        classUnderTest.hasBypassedRequest = true

        verify(mockPrefEditor).putBoolean(bypassedRequest, true)
    }
}