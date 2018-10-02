package com.chesire.zwei.util

import android.content.Context
import android.content.SharedPreferences
import com.chesire.zwei.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

private const val bypassedWelcome = "bypassedWelcome"
private const val bypassedRequest = "bypassedRequest"
private const val acquiredCharacter = "acquiredCharacter"

class PrefHelperTests {
    private val mockContext = mock<Context> {
        on {
            getString(R.string.key_bypassed_welcome)
        }.thenReturn(bypassedWelcome)
        on {
            getString(R.string.key_bypassed_request)
        }.thenReturn(bypassedRequest)
        on {
            getString(R.string.key_acquired_character)
        }.thenReturn(acquiredCharacter)
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

    @Test
    fun `hasAcquiredCharacter set() changes value in SharedPreferences`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)
        classUnderTest.hasAcquiredCharacter = true

        verify(mockPrefEditor).putBoolean(acquiredCharacter, true)
    }

    @Test
    fun `shouldDisplayOnboarding returns true if have not bypassedWelcome`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
            on {
                getBoolean(bypassedWelcome, false)
            }.thenReturn(false)
            on {
                getBoolean(bypassedRequest, false)
            }.thenReturn(true)
            on {
                getBoolean(acquiredCharacter, false)
            }.thenReturn(true)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)

        Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
    }

    @Test
    fun `shouldDisplayOnboarding returns true if have not bypassedRequest`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
            on {
                getBoolean(bypassedWelcome, false)
            }.thenReturn(true)
            on {
                getBoolean(bypassedRequest, false)
            }.thenReturn(false)
            on {
                getBoolean(acquiredCharacter, false)
            }.thenReturn(true)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)

        Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
    }

    @Test
    fun `shouldDisplayOnboarding returns true if have not acquiredCharacter`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
            on {
                getBoolean(bypassedWelcome, false)
            }.thenReturn(true)
            on {
                getBoolean(bypassedRequest, false)
            }.thenReturn(true)
            on {
                getBoolean(acquiredCharacter, false)
            }.thenReturn(false)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)

        Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
    }

    @Test
    fun `shouldDisplayOnboarding returns true if have not bypassedWelcome && bypassedRequest && acquiredCharacter`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
            on {
                getBoolean(bypassedWelcome, false)
            }.thenReturn(false)
            on {
                getBoolean(bypassedRequest, false)
            }.thenReturn(false)
            on {
                getBoolean(acquiredCharacter, false)
            }.thenReturn(false)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)

        Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
    }

    @Test
    fun `shouldDisplayOnboarding returns false if have bypassedWelcome && bypassedRequest && acquiredCharacter`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
            on {
                getBoolean(bypassedWelcome, false)
            }.thenReturn(true)
            on {
                getBoolean(bypassedRequest, false)
            }.thenReturn(true)
            on {
                getBoolean(acquiredCharacter, false)
            }.thenReturn(true)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)

        Assert.assertFalse(classUnderTest.shouldDisplayOnboarding)
    }

    @Test(expected = IllegalStateException::class)
    @Ignore("Can't test this for now, as using properties stops invalid data being passed in")
    fun `invalid data causes an IllegalStateException`() {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on {
                edit()
            }.thenReturn(mockPrefEditor)
        }

        val classUnderTest = PrefHelper(mockContext, mockPreferences)
    }
}
