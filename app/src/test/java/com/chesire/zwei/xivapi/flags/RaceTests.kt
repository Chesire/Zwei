package com.chesire.zwei.xivapi.flags

import org.junit.Assert
import org.junit.Test

class RaceTests {
    @Test
    fun `getRaceForValue with '0' returns 'Unknown'`() {
        Assert.assertEquals(Race.Unknown, Race.getRaceForValue(0))
    }

    @Test
    fun `getRaceForValue with '99' returns 'Unknown'`() {
        Assert.assertEquals(Race.Unknown, Race.getRaceForValue(99))
    }

    @Test
    fun `getRaceForValue with '1' returns 'Hyur'`() {
        Assert.assertEquals(Race.Hyur, Race.getRaceForValue(1))
    }

    @Test
    fun `getRaceForValue with '2' returns 'Elezen'`() {
        Assert.assertEquals(Race.Elezen, Race.getRaceForValue(2))
    }

    @Test
    fun `getRaceForValue with '3' returns 'Lalafell'`() {
        Assert.assertEquals(Race.Lalafell, Race.getRaceForValue(3))
    }

    @Test
    fun `getRaceForValue with '4' returns 'Miqote'`() {
        Assert.assertEquals(Race.Miqote, Race.getRaceForValue(4))
    }

    @Test
    fun `getRaceForValue with '5' returns 'Roegadyn'`() {
        Assert.assertEquals(Race.Roegadyn, Race.getRaceForValue(5))
    }

    @Test
    fun `getRaceForValue with '6' returns 'AuRa'`() {
        Assert.assertEquals(Race.AuRa, Race.getRaceForValue(6))
    }
}