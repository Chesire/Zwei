package com.chesire.zwei.room.converters

import com.chesire.zwei.xivapi.model.FreeCompanyEstateModel
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert

class FreeCompanyEstateModelConverterTests : Spek({
    val converter by memoized { FreeCompanyEstateModelConverter() }
    val estateModelJson = "{\"Greeting\":\"Greeting\",\"Name\":\"Name\",\"Plot\":\"Plot\"}"
    val estateModel = FreeCompanyEstateModel(
        "Greeting",
        "Name",
        "Plot"
    )

    given("a FreeCompanyEstateModel") {
        it("can convert into a json string") {
            Assert.assertEquals(estateModelJson, converter.fromFreeCompanyEstateModel(estateModel))
        }
    }

    given("a json string") {
        it("can convert into a FreeCompanyEstateModel") {
            Assert.assertEquals(estateModel, converter.fromString(estateModelJson))
        }
    }
})
