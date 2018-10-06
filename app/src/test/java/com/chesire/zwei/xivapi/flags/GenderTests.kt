package com.chesire.zwei.xivapi.flags

import org.junit.Assert
import org.junit.Test

class GenderTests {
    @Test
    fun `getGenderForValue with '0' returns 'Unknown'`() {
        Assert.assertEquals(Gender.Unknown, Gender.getGenderForValue(0))
    }

    @Test
    fun `getGenderForValue with '99' returns 'Unknown'`() {
        Assert.assertEquals(Gender.Unknown, Gender.getGenderForValue(99))
    }

    @Test
    fun `getGenderForValue with '1' returns 'Male'`() {
        Assert.assertEquals(Gender.Male, Gender.getGenderForValue(1))
    }

    @Test
    fun `getGenderForValue with '2' returns 'Female'`() {
        Assert.assertEquals(Gender.Female, Gender.getGenderForValue(2))
    }
}
