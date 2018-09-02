package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationModel(
    @Json(name = "page")
    val page: Int,
    @Json(name = "page_next")
    val pageNext: Int?,
    @Json(name = "page_prev")
    val pagePrev: Int?,
    @Json(name = "page_total")
    val pageTotal: Int,
    @Json(name = "results")
    val results: Int,
    @Json(name = "results_per_page")
    val resultsPerPage: Int,
    @Json(name = "results_total")
    val resultsTotal: Int
)