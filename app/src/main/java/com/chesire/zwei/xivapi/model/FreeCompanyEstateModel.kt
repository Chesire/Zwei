package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FreeCompanyEstateModel(
    @Json(name = "Greeting")
    val greeting: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Plot")
    val plot: String
)
