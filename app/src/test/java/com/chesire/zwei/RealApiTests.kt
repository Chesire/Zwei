package com.chesire.zwei

import com.chesire.zwei.xivapi.XIVApiManager
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Tests to hit the real api.
 *
 * These are here just to attach the debugger and see the results from the calls.
 */
class RealApiTests {
    private lateinit var manager: XIVApiManager

    @Before
    fun setup() {
        manager = XIVApiManager()
    }

    @After
    fun teardown() {
        // Nothing currently
    }

    @Test
    fun `search for character Cheshire Cat on Phoenix`() {
        manager.searchForCharacter("Cheshire Cat", "Phoenix")
    }
}
