package com.chesire.zwei.room.converters

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert

class CollectionsConverterTests : Spek({
    val converter by memoized { CollectionsConverter() }

    val string1 = "string1"
    val string2 = "string2"
    val string3 = "string3"
    val int1 = 1
    val int2 = 2
    val int3 = 3

    val stringJson = "[\"$string1\",\"$string2\",\"$string3\"]"
    val intJson = "[$int1,$int2,$int3]"
    val stringList = listOf(string1, string2, string3)
    val intList = listOf(int1, int2, int3)

    given("listOf string") {
        it("can convert into json string") {
            Assert.assertEquals(stringJson, converter.fromListOfStringToString(stringList))
        }
    }

    given("listOf int") {
        it("can convert into json string") {
            Assert.assertEquals(intJson, converter.fromListOfIntToString(intList))
        }
    }

    given("valid json string") {
        it("can convert into listOf string") {
            Assert.assertEquals(stringList, converter.fromStringToListOfString(stringJson))
        }

        it("can convert into listOf int") {
            Assert.assertEquals(intList, converter.fromStringToListOfInt(intJson))
        }
    }
})
