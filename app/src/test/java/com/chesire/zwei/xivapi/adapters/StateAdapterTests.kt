package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.State
import com.squareup.moshi.JsonDataException
import org.junit.Assert
import org.junit.Test

class StateAdapterTests {
    private val stateAdapter = StateAdapter()

    @Test
    fun `'None' toJson produces 0`() {
        Assert.assertEquals(0, stateAdapter.toJson(State.None))
    }

    @Test
    fun `'Adding' toJson produces 1`() {
        Assert.assertEquals(1, stateAdapter.toJson(State.Adding))
    }

    @Test
    fun `'Cached' toJson produces 2`() {
        Assert.assertEquals(2, stateAdapter.toJson(State.Cached))
    }

    @Test
    fun `'NotFound' toJson produces 3`() {
        Assert.assertEquals(3, stateAdapter.toJson(State.NotFound))
    }

    @Test
    fun `'Blacklist' toJson produces 4`() {
        Assert.assertEquals(4, stateAdapter.toJson(State.Blacklist))
    }

    @Test
    fun `'Private' toJson produces 5`() {
        Assert.assertEquals(5, stateAdapter.toJson(State.Private))
    }

    @Test
    fun `0 fromJson produces State 'None'`() {
        Assert.assertEquals(stateAdapter.fromJson(0), State.None)
    }

    @Test
    fun `1 fromJson produces State 'Adding'`() {
        Assert.assertEquals(stateAdapter.fromJson(1), State.Adding)
    }

    @Test
    fun `2 fromJson produces State 'Cached'`() {
        Assert.assertEquals(stateAdapter.fromJson(2), State.Cached)
    }

    @Test
    fun `3 fromJson produces State 'NotFound'`() {
        Assert.assertEquals(stateAdapter.fromJson(3), State.NotFound)
    }

    @Test
    fun `4 fromJson produces State 'Blacklist'`() {
        Assert.assertEquals(stateAdapter.fromJson(4), State.Blacklist)
    }

    @Test
    fun `5 fromJson produces State 'Private'`() {
        Assert.assertEquals(stateAdapter.fromJson(5), State.Private)
    }

    @Test(expected = JsonDataException::class)
    fun `-1 fromJson produces JsonDataException`() {
        stateAdapter.fromJson(-1)
    }

    @Test(expected = JsonDataException::class)
    fun `6 fromJson produces JsonDataException`() {
        stateAdapter.fromJson(6)
    }
}