package com.chesire.zwei.xivapi.flags

import org.junit.Assert.assertEquals
import org.junit.Test

class GenderTests {
    @Test
    fun `getGenderForValue with '0' returns 'Unknown'`() {
        assertEquals(Gender.Unknown, Gender.getGenderForValue(0))
    }

    @Test
    fun `getGenderForValue with '99' returns 'Unknown'`() {
        assertEquals(Gender.Unknown, Gender.getGenderForValue(99))
    }

    @Test
    fun `getGenderForValue with '1' returns 'Male'`() {
        assertEquals(Gender.Male, Gender.getGenderForValue(1))
    }

    @Test
    fun `getGenderForValue with '2' returns 'Female'`() {
        assertEquals(Gender.Female, Gender.getGenderForValue(2))
    }
}
