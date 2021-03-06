package com.chesire.zwei.room.converters

import com.chesire.zwei.xivapi.flags.Gender
import org.junit.Assert.assertEquals
import org.junit.Test

class GenderConverterTests {
    private val converter = GenderConverter()

    @Test
    fun `can convert gender into int`() {
        assertEquals(1, converter.fromGenderToInt(Gender.getGenderForValue(1)))
    }

    @Test
    fun `can convert int into gender`() {
        assertEquals(Gender.getGenderForValue(1), converter.fromIntToGender(1))
    }
}
