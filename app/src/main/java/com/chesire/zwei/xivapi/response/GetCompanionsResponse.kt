package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.CompanionModel
import com.chesire.zwei.xivapi.model.PaginationModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCompanionsResponse(
    @Json(name = "Pagination")
    val pagination: PaginationModel,
    @Json(name = "Results")
    val companions: List<CompanionModel>
)