package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TitleModel(
    @Json(name = "ID")
    val id: Int,
    @Json(name = "IsPrefix")
    val isPrefix: Int,
    @Json(name = "NameFemale")
    val nameFemale: String,
    @Json(name = "Name")
    val nameMale: String
)