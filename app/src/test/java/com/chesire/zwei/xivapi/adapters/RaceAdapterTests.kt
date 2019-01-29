package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.Race
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Test

class RaceAdapterTests {
    private val raceAdapter = RaceAdapter()
    private val moshi = Moshi.Builder()
        .add(raceAdapter)
        .build()
    private val moshiAdapter = moshi.adapter(Race::class.java)

    @Test
    fun `'Hyur' toJson produces value '1'`() {
        assertEquals(1, raceAdapter.toJson(Race.Hyur))
    }

    @Test
    fun `'Elezen' toJson produces value '2'`() {
        assertEquals(2, raceAdapter.toJson(Race.Elezen))
    }

    @Test
    fun `'Lalafell' toJson produces value '3'`() {
        assertEquals(3, raceAdapter.toJson(Race.Lalafell))
    }

    @Test
    fun `'Miqote' toJson produces value '4'`() {
        assertEquals(4, raceAdapter.toJson(Race.Miqote))
    }

    @Test
    fun `'Roegadyn' toJson produces value '5'`() {
        assertEquals(5, raceAdapter.toJson(Race.Roegadyn))
    }

    @Test
    fun `'AuRa' toJson produces value '6'`() {
        assertEquals(6, raceAdapter.toJson(Race.AuRa))
    }

    @Test
    fun `Moshi with adapter converts 'Hyur' to '1'`() {
        assertEquals("1", moshiAdapter.toJson(Race.Hyur))
    }

    @Test
    fun `Moshi with adapter converts 'Elezen' to '2'`() {
        assertEquals("2", moshiAdapter.toJson(Race.Elezen))
    }

    @Test
    fun `Moshi with adapter converts 'Lalafell' to '3'`() {
        assertEquals("3", moshiAdapter.toJson(Race.Lalafell))
    }

    @Test
    fun `Moshi with adapter converts 'Miqote' to '4'`() {
        assertEquals("4", moshiAdapter.toJson(Race.Miqote))
    }

    @Test
    fun `Moshi with adapter converts 'Roegadyn' to '5'`() {
        assertEquals("5", moshiAdapter.toJson(Race.Roegadyn))
    }

    @Test
    fun `Moshi with adapter converts 'AuRa' to '6'`() {
        assertEquals("6", moshiAdapter.toJson(Race.AuRa))
    }

    @Test
    fun `'1' fromJson produces Race 'Hyur'`() {
        assertEquals(Race.Hyur, raceAdapter.fromJson(1))
    }

    @Test
    fun `'2' fromJson produces Race 'Elezen'`() {
        assertEquals(Race.Elezen, raceAdapter.fromJson(2))
    }

    @Test
    fun `'3' fromJson produces Race 'Lalafell'`() {
        assertEquals(Race.Lalafell, raceAdapter.fromJson(3))
    }

    @Test
    fun `'4' fromJson produces Race 'Miqote'`() {
        assertEquals(Race.Miqote, raceAdapter.fromJson(4))
    }

    @Test
    fun `'5' fromJson produces Race 'Roegadyn'`() {
        assertEquals(Race.Roegadyn, raceAdapter.fromJson(5))
    }

    @Test
    fun `'6' fromJson produces Race 'AuRa'`() {
        assertEquals(Race.AuRa, raceAdapter.fromJson(6))
    }

    @Test
    fun `Moshi with adapter converts '1' to 'Hyur'`() {
        assertEquals(Race.Hyur, moshiAdapter.fromJson("1"))
    }

    @Test
    fun `Moshi with adapter converts '2' to 'Elezen'`() {
        assertEquals(Race.Elezen, moshiAdapter.fromJson("2"))
    }

    @Test
    fun `Moshi with adapter converts '3' to 'Lalafell'`() {
        assertEquals(Race.Lalafell, moshiAdapter.fromJson("3"))
    }

    @Test
    fun `Moshi with adapter converts '4' to 'Miqote'`() {
        assertEquals(Race.Miqote, moshiAdapter.fromJson("4"))
    }

    @Test
    fun `Moshi with adapter converts '5' to 'Roegadyn'`() {
        assertEquals(Race.Roegadyn, moshiAdapter.fromJson("5"))
    }

    @Test
    fun `Moshi with adapter converts '6' to 'AuRa'`() {
        assertEquals(Race.AuRa, moshiAdapter.fromJson("6"))
    }

    @Test(expected = JsonDataException::class)
    fun `'-1' fromJson produces JsonDataException`() {
        raceAdapter.fromJson(0)
    }

    @Test(expected = JsonDataException::class)
    fun `'0' fromJson produces JsonDataException`() {
        raceAdapter.fromJson(0)
    }

    @Test(expected = JsonDataException::class)
    fun `'7' fromJson produces JsonDataException`() {
        raceAdapter.fromJson(7)
    }
}
