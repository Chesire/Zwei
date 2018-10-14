package com.chesire.zwei.room.converters

import com.chesire.zwei.xivapi.flags.Race
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert

class RaceConverterTests : Spek({
    val converter by memoized { RaceConverter() }
    val expectedInt = 1
    val expectedRace = Race.getRaceForValue(1)

    given("a Race") {
        it("can convert into an int") {
            Assert.assertEquals(expectedInt, converter.fromRaceToInt(expectedRace))
        }
    }

    given("an int") {
        it("can convert into a Gender") {
            Assert.assertEquals(expectedRace, converter.fromIntToRace(expectedInt))
        }
    }
})
