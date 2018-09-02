package com.chesire.zwei

import com.chesire.zwei.xivapi.XIVApiManager
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

/**
 * Tests to hit the real api.
 *
 * These are here just to attach the debugger and see the results from the calls.
 */
@Ignore
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
    fun `search for character 'Cheshire Cat' on 'Phoenix'`() {
        manager.searchForCharacter("Cheshire Cat", "Phoenix")
    }

    @Test
    fun `get character 'Cheshire Cat' on 'Phoenix'`() {
        manager.getCharacter(2839897)
    }

    @Test
    fun `request character 'Cheshire Cat' on 'Phoenix' be updated`() {
        manager.requestCharacterUpdate(2839897)
    }

    @Test
    fun `gets data for companion '1'`() {
        manager.getCompanion(1)
    }

    @Test
    fun `gets data for all companions`() {
        manager.getCompanions()
    }

    @Test
    fun `gets data for all mounts`() {
        manager.getMounts()
    }

    @Test
    fun `gets data for title '1'`() {
        manager.getTitle(1)
    }

    @Test
    fun `gets data for all titles`() {
        manager.getTitles()
    }
}
