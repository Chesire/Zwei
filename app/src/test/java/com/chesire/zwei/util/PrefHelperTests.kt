package com.chesire.zwei.util

import android.content.Context
import android.content.SharedPreferences
import com.chesire.zwei.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert

private const val bypassedWelcome = "bypassedWelcome"
private const val bypassedRequest = "bypassedRequest"
private const val acquiredCharacter = "acquiredCharacter"

class PrefHelperTests : Spek({
    val mockContext = mock<Context> {
        on { getString(R.string.key_bypassed_welcome) }.thenReturn(bypassedWelcome)
        on { getString(R.string.key_bypassed_request) }.thenReturn(bypassedRequest)
        on { getString(R.string.key_acquired_character) }.thenReturn(acquiredCharacter)
    }

    context("the set methods") {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }
        val mockPreferences = mock<SharedPreferences> {
            on { edit() }.thenReturn(mockPrefEditor)
        }

        test("hasBypassedWelcome, changes the value in SharedPreferences") {
            val classUnderTest = PrefHelper(mockContext, mockPreferences)
            classUnderTest.hasBypassedWelcome = true

            verify(mockPrefEditor).putBoolean(bypassedWelcome, true)
        }

        test("hasBypassedRequest, changes the value in SharedPreferences") {
            val classUnderTest = PrefHelper(mockContext, mockPreferences)
            classUnderTest.hasBypassedRequest = true

            verify(mockPrefEditor).putBoolean(bypassedRequest, true)
        }

        test("hasAcquiredCharacter, changes the value in SharedPreferences") {
            val classUnderTest = PrefHelper(mockContext, mockPreferences)
            classUnderTest.hasAcquiredCharacter = true

            verify(mockPrefEditor).putBoolean(acquiredCharacter, true)
        }
    }

    given("shouldDisplayOnboarding") {
        val mockPrefEditor = mock<SharedPreferences.Editor> { }

        group("returns true if") {
            test("bypassedWelcome is false") {
                val mockPreferences = mock<SharedPreferences> {
                    on { edit() }.thenReturn(mockPrefEditor)
                    on { getBoolean(bypassedWelcome, false) }.thenReturn(false)
                    on { getBoolean(bypassedRequest, false) }.thenReturn(true)
                    on { getBoolean(acquiredCharacter, false) }.thenReturn(true)
                }

                val classUnderTest = PrefHelper(mockContext, mockPreferences)

                Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
            }

            test("bypassedRequest is false") {
                val mockPreferences = mock<SharedPreferences> {
                    on { edit() }.thenReturn(mockPrefEditor)
                    on { getBoolean(bypassedWelcome, false) }.thenReturn(true)
                    on { getBoolean(bypassedRequest, false) }.thenReturn(false)
                    on { getBoolean(acquiredCharacter, false) }.thenReturn(true)
                }

                val classUnderTest = PrefHelper(mockContext, mockPreferences)

                Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
            }

            test("acquiredCharacter is false") {
                val mockPreferences = mock<SharedPreferences> {
                    on { edit() }.thenReturn(mockPrefEditor)
                    on { getBoolean(bypassedWelcome, false) }.thenReturn(true)
                    on { getBoolean(bypassedRequest, false) }.thenReturn(true)
                    on { getBoolean(acquiredCharacter, false) }.thenReturn(false)
                }

                val classUnderTest = PrefHelper(mockContext, mockPreferences)

                Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
            }

            test("bypassedWelcome, bypassedRequest, and acquiredCharacter are false") {
                val mockPreferences = mock<SharedPreferences> {
                    on { edit() }.thenReturn(mockPrefEditor)
                    on { getBoolean(bypassedWelcome, false) }.thenReturn(false)
                    on { getBoolean(bypassedRequest, false) }.thenReturn(false)
                    on { getBoolean(acquiredCharacter, false) }.thenReturn(false)
                }

                val classUnderTest = PrefHelper(mockContext, mockPreferences)

                Assert.assertTrue(classUnderTest.shouldDisplayOnboarding)
            }
        }

        group("returns false if") {
            test("bypassedWelcome, bypassedRequest, and acquiredCharacter are false") {
                val mockPreferences = mock<SharedPreferences> {
                    on { edit() }.thenReturn(mockPrefEditor)
                    on { getBoolean(bypassedWelcome, false) }.thenReturn(true)
                    on { getBoolean(bypassedRequest, false) }.thenReturn(true)
                    on { getBoolean(acquiredCharacter, false) }.thenReturn(true)
                }

                val classUnderTest = PrefHelper(mockContext, mockPreferences)

                Assert.assertFalse(classUnderTest.shouldDisplayOnboarding)
            }
        }
    }

    given("clear method") {
        val mockPrefEditor = mock<SharedPreferences.Editor> {
            on { clear() }.thenReturn(mock)
        }
        val mockPreferences = mock<SharedPreferences> {
            on { edit() }.thenReturn(mockPrefEditor)
        }

        it("clears the shared preferences data") {
            val classUnderTest = PrefHelper(mockContext, mockPreferences)

            classUnderTest.clear()

            verify(mockPrefEditor).clear()
        }
    }
})
