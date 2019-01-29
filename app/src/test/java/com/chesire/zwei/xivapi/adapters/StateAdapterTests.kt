package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.State
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Test

class StateAdapterTests {
    private val stateAdapter = StateAdapter()
    private val moshi = Moshi.Builder()
        .add(stateAdapter)
        .build()
    private val moshiAdapter = moshi.adapter(State::class.java)

    @Test
    fun `'None' toJson produces '0'`() {
        assertEquals(0, stateAdapter.toJson(State.None))
    }

    @Test
    fun `'Adding' toJson produces '1'`() {
        assertEquals(1, stateAdapter.toJson(State.Adding))
    }

    @Test
    fun `'Cached' toJson produces '2'`() {
        assertEquals(2, stateAdapter.toJson(State.Cached))
    }

    @Test
    fun `'NotFound' toJson produces '3'`() {
        assertEquals(3, stateAdapter.toJson(State.NotFound))
    }

    @Test
    fun `'Blacklist' toJson produces '4'`() {
        assertEquals(4, stateAdapter.toJson(State.Blacklist))
    }

    @Test
    fun `'Private' toJson produces '5'`() {
        assertEquals(5, stateAdapter.toJson(State.Private))
    }

    @Test
    fun `Moshi with adapter converts 'None' to '0'`() {
        assertEquals("0", moshiAdapter.toJson(State.None))
    }

    @Test
    fun `Moshi with adapter converts 'Adding' to '1'`() {
        assertEquals("1", moshiAdapter.toJson(State.Adding))
    }

    @Test
    fun `Moshi with adapter converts 'Cached' to '2'`() {
        assertEquals("2", moshiAdapter.toJson(State.Cached))
    }

    @Test
    fun `Moshi with adapter converts 'NotFound' to '3'`() {
        assertEquals("3", moshiAdapter.toJson(State.NotFound))
    }

    @Test
    fun `Moshi with adapter converts 'Blacklist' to '4'`() {
        assertEquals("4", moshiAdapter.toJson(State.Blacklist))
    }

    @Test
    fun `Moshi with adapter converts 'Private' to '5'`() {
        assertEquals("5", moshiAdapter.toJson(State.Private))
    }

    @Test
    fun `'0' fromJson produces State 'None'`() {
        assertEquals(stateAdapter.fromJson(0), State.None)
    }

    @Test
    fun `'1' fromJson produces State 'Adding'`() {
        assertEquals(stateAdapter.fromJson(1), State.Adding)
    }

    @Test
    fun `'2' fromJson produces State 'Cached'`() {
        assertEquals(stateAdapter.fromJson(2), State.Cached)
    }

    @Test
    fun `'3' fromJson produces State 'NotFound'`() {
        assertEquals(stateAdapter.fromJson(3), State.NotFound)
    }

    @Test
    fun `'4' fromJson produces State 'Blacklist'`() {
        assertEquals(stateAdapter.fromJson(4), State.Blacklist)
    }

    @Test
    fun `'5' fromJson produces State 'Private'`() {
        assertEquals(stateAdapter.fromJson(5), State.Private)
    }

    @Test
    fun `Moshi was adapter converts '0' to 'None'`() {
        assertEquals(moshiAdapter.fromJson("0"), State.None)
    }

    @Test
    fun `Moshi was adapter converts '1' to 'Adding'`() {
        assertEquals(moshiAdapter.fromJson("1"), State.Adding)
    }

    @Test
    fun `Moshi was adapter converts '2' to 'Cached'`() {
        assertEquals(moshiAdapter.fromJson("2"), State.Cached)
    }

    @Test
    fun `Moshi was adapter converts '3' to 'NotFound'`() {
        assertEquals(moshiAdapter.fromJson("3"), State.NotFound)
    }

    @Test
    fun `Moshi was adapter converts '4' to 'Blacklist'`() {
        assertEquals(moshiAdapter.fromJson("4"), State.Blacklist)
    }

    @Test
    fun `Moshi was adapter converts '5' to 'Private'`() {
        assertEquals(moshiAdapter.fromJson("5"), State.Private)
    }

    @Test(expected = JsonDataException::class)
    fun `'-1' fromJson produces JsonDataException`() {
        stateAdapter.fromJson(-1)
    }

    @Test(expected = JsonDataException::class)
    fun `'6' fromJson produces JsonDataException`() {
        stateAdapter.fromJson(6)
    }
}
