package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.PaginationModel
import com.chesire.zwei.xivapi.model.TitleModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetTitlesResponse(
    @Json(name = "pagination")
    val pagination: PaginationModel,
    @Json(name = "results")
    val titles: List<TitleModel>
)