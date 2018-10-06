package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationModel(
    @Json(name = "Page")
    val page: Int,
    @Json(name = "PageNext")
    val pageNext: Int?,
    @Json(name = "PagePrev")
    val pagePrev: Int?,
    @Json(name = "PageTotal")
    val pageTotal: Int,
    @Json(name = "Results")
    val results: Int,
    @Json(name = "ResultsPerPage")
    val resultsPerPage: Int,
    @Json(name = "ResultsTotal")
    val resultsTotal: Int
)
