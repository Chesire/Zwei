package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.SearchCharacterModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchCharacterResponse(
    @Json(name = "Characters")
    val characters: List<SearchCharacterModel>,
    @Json(name = "PageCurrent")
    val pageCurrent: Int,
    @Json(name = "PageNext")
    val pageNext: Int,
    @Json(name = "PagePrevious")
    val pagePrevious: Int,
    @Json(name = "PageTotal")
    val pageTotal: Int,
    @Json(name = "Total")
    val total: Int
)