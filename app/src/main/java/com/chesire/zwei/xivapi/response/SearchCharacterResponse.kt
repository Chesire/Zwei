package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.PaginationModel
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchCharacterResponse(
    @Json(name = "Pagination")
    val pagination: PaginationModel,
    @Json(name = "Results")
    val characters: List<SearchCharacterModel>
)
