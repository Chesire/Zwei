package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.Race
import com.squareup.moshi.JsonDataException
import org.junit.Assert
import org.junit.Test

class RaceAdapterTests {
    private val raceAdapter = RaceAdapter()

    @Test
    fun `'Hyur' toJson produces value 1`() {
        Assert.assertEquals(1, raceAdapter.toJson(Race.Hyur))
    }

    @Test
    fun `'Elezen' toJson produces value '2'`() {
        Assert.assertEquals(2, raceAdapter.toJson(Race.Elezen))
    }

    @Test
    fun `'Lalafell' toJson produces value '3'`() {
        Assert.assertEquals(3, raceAdapter.toJson(Race.Lalafell))
    }

    @Test
    fun `'Miqote' toJson produces value '4'`() {
        Assert.assertEquals(4, raceAdapter.toJson(Race.Miqote))
    }

    @Test
    fun `'Roegadyn' toJson produces value '5'`() {
        Assert.assertEquals(5, raceAdapter.toJson(Race.Roegadyn))
    }

    @Test
    fun `'AuRa' toJson produces value '6'`() {
        Assert.assertEquals(6, raceAdapter.toJson(Race.AuRa))
    }

    @Test
    fun `1 fromJson produces Race 'Hyur'`() {
        Assert.assertEquals(Race.Hyur, raceAdapter.fromJson(1))
    }

    @Test
    fun `2 fromJson produces Race 'Elezen'`() {
        Assert.assertEquals(Race.Elezen, raceAdapter.fromJson(2))
    }

    @Test
    fun `3 fromJson produces Race 'Lalafell'`() {
        Assert.assertEquals(Race.Lalafell, raceAdapter.fromJson(3))
    }

    @Test
    fun `4 fromJson produces Race 'Miqote'`() {
        Assert.assertEquals(Race.Miqote, raceAdapter.fromJson(4))
    }

    @Test
    fun `5 fromJson produces Race 'Roegadyn'`() {
        Assert.assertEquals(Race.Roegadyn, raceAdapter.fromJson(5))
    }

    @Test
    fun `6 fromJson produces Race 'AuRa'`() {
        Assert.assertEquals(Race.AuRa, raceAdapter.fromJson(6))
    }

    @Test(expected = JsonDataException::class)
    fun `0 fromJson produces JsonDataException`() {
        raceAdapter.fromJson(0)
    }
}