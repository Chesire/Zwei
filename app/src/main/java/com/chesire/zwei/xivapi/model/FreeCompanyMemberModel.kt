package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FreeCompanyMemberModel(
    @Json(name = "Avatar")
    val avatar: String,
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Rank")
    val rank: String,
    @Json(name = "RankIcon")
    val rankIcon: String,
    @Json(name = "Server")
    val server: String
)
