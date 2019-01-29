package com.chesire.zwei.room.converters

import com.chesire.zwei.xivapi.model.FreeCompanyEstateModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class FreeCompanyEstateModelConverterTests {
    private val converter = FreeCompanyEstateModelConverter()
    private val estateModelJson = "{\"Greeting\":\"Greeting\",\"Name\":\"Name\",\"Plot\":\"Plot\"}"
    private val estateModel = FreeCompanyEstateModel(
        "Greeting",
        "Name",
        "Plot"
    )

    @Test
    fun `can convert FreeCompanyEstateModel into json string`() {
        assertEquals(estateModelJson, converter.fromFreeCompanyEstateModel(estateModel))
    }

    @Test
    fun `can convert json string into FreeCompanyEstateModel`() {
        assertEquals(estateModel, converter.fromString(estateModelJson))
    }
}
