package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.Race
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert
import org.junit.Test

class RaceAdapterTests {
    private val raceAdapter = RaceAdapter()
    private val moshi = Moshi.Builder()
        .add(raceAdapter)
        .build()
    private val moshiAdapter = moshi.adapter(Race::class.java)

    @Test
    fun `'Hyur' toJson produces value '1'`() {
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
    fun `Moshi with adapter converts 'Hyur' to '1'`() {
        Assert.assertEquals("1", moshiAdapter.toJson(Race.Hyur))
    }

    @Test
    fun `Moshi with adapter converts 'Elezen' to '2'`() {
        Assert.assertEquals("2", moshiAdapter.toJson(Race.Elezen))
    }

    @Test
    fun `Moshi with adapter converts 'Lalafell' to '3'`() {
        Assert.assertEquals("3", moshiAdapter.toJson(Race.Lalafell))
    }

    @Test
    fun `Moshi with adapter converts 'Miqote' to '4'`() {
        Assert.assertEquals("4", moshiAdapter.toJson(Race.Miqote))
    }

    @Test
    fun `Moshi with adapter converts 'Roegadyn' to '5'`() {
        Assert.assertEquals("5", moshiAdapter.toJson(Race.Roegadyn))
    }

    @Test
    fun `Moshi with adapter converts 'AuRa' to '6'`() {
        Assert.assertEquals("6", moshiAdapter.toJson(Race.AuRa))
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

    @Test
    fun `Moshi with adapter converts '1' to 'Hyur'`() {
        Assert.assertEquals(Race.Hyur, moshiAdapter.fromJson("1"))
    }

    @Test
    fun `Moshi with adapter converts '2' to 'Elezen'`() {
        Assert.assertEquals(Race.Elezen, moshiAdapter.fromJson("2"))
    }

    @Test
    fun `Moshi with adapter converts '3' to 'Lalafell'`() {
        Assert.assertEquals(Race.Lalafell, moshiAdapter.fromJson("3"))
    }

    @Test
    fun `Moshi with adapter converts '4' to 'Miqote'`() {
        Assert.assertEquals(Race.Miqote, moshiAdapter.fromJson("4"))
    }

    @Test
    fun `Moshi with adapter converts '5' to 'Roegadyn'`() {
        Assert.assertEquals(Race.Roegadyn, moshiAdapter.fromJson("5"))
    }

    @Test
    fun `Moshi with adapter converts '6' to 'AuRa'`() {
        Assert.assertEquals(Race.AuRa, moshiAdapter.fromJson("6"))
    }

    @Test(expected = JsonDataException::class)
    fun `-1 fromJson produces JsonDataException`() {
        raceAdapter.fromJson(0)
    }

    @Test(expected = JsonDataException::class)
    fun `0 fromJson produces JsonDataException`() {
        raceAdapter.fromJson(0)
    }

    @Test(expected = JsonDataException::class)
    fun `7 fromJson produces JsonDataException`() {
        raceAdapter.fromJson(7)
    }
}