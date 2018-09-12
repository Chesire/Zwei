package com.chesire.zwei.room.converters

import org.junit.Assert
import org.junit.Test

class CollectionsConverterTests {
    private val converter = CollectionsConverter()

    @Test
    fun `can convert listOf string into json string`() {
        val string1 = "string1"
        val string2 = "string2"
        val string3 = "string3"

        val jsonString = converter.fromListOfString(listOf(string1, string2, string3))
        Assert.assertEquals("[\"$string1\",\"$string2\",\"$string3\"]", jsonString)
    }

    @Test
    fun `can convert json string into listOf string`() {
        val string1 = "string1"
        val string2 = "string2"
        val string3 = "string3"

        val stringList = converter.fromString("[\"$string1\",\"$string2\",\"$string3\"]")
        Assert.assertEquals(listOf(string1, string2, string3), stringList)
    }
}