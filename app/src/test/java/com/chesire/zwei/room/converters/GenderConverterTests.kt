package com.chesire.zwei.room.converters

import com.chesire.zwei.xivapi.flags.Gender
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert

class GenderConverterTests : Spek({
    val converter by memoized { GenderConverter() }
    val expectedInt = 1
    val expectedGender = Gender.getGenderForValue(1)

    given("a Gender") {
        it("can convert into an int") {
            Assert.assertEquals(expectedInt, converter.fromGenderToInt(expectedGender))
        }
    }

    given("an int") {
        it("can convert into a Gender") {
            Assert.assertEquals(expectedGender, converter.fromIntToGender(expectedInt))
        }
    }
})
