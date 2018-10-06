package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AchievementsModel(
    @Json(name = "List")
    val list: List<Map<String, Int>>,
    @Json(name = "ParseDate")
    val parseDate: String,
    @Json(name = "Points")
    val points: Int
)
