package com.chesire.zwei.room.converters

import com.chesire.zwei.xivapi.flags.Race
import org.junit.Assert.assertEquals
import org.junit.Test

class RaceConverterTests {
    private val converter = RaceConverter()

    @Test
    fun `can convert race into int`() {
        assertEquals(1, converter.fromRaceToInt(Race.getRaceForValue(1)))
    }

    @Test
    fun `can convert int into race`() {
        assertEquals(Race.getRaceForValue(1), converter.fromIntToRace(1))
    }
}
