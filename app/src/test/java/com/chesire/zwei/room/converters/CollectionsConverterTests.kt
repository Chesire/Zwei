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

        val jsonString = converter.fromListOfStringToString(listOf(string1, string2, string3))
        Assert.assertEquals("[\"$string1\",\"$string2\",\"$string3\"]", jsonString)
    }

    @Test
    fun `can convert json string into listOf string`() {
        val string1 = "string1"
        val string2 = "string2"
        val string3 = "string3"

        val stringList =
            converter.fromStringToListOfString("[\"$string1\",\"$string2\",\"$string3\"]")
        Assert.assertEquals(listOf(string1, string2, string3), stringList)
    }

    @Test
    fun `can convert listOf int into json string`() {
        val int1 = 1
        val int2 = 2
        val int3 = 3

        val jsonString = converter.fromListOfIntToString(listOf(int1, int2, int3))
        Assert.assertEquals("[1,2,3]", jsonString)
    }

    @Test
    fun `can convert json string into listOf int`() {
        val int1 = 1
        val int2 = 2
        val int3 = 3

        val stringList = converter.fromStringToListOfInt("[1,2,3]")
        Assert.assertEquals(listOf(int1, int2, int3), stringList)
    }
}