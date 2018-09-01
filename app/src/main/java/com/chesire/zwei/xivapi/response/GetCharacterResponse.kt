package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.InfoModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCharacterResponse(
    @Json(name = "Info")
    val info: InfoModel
)