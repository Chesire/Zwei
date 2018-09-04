package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FreeCompanyModel(
    @Json(name = "ActiveMemberCount")
    val activeMemberCount: Int,
    @Json(name = "Crest")
    val crest: List<String>,
    @Json(name = "Estate")
    val estate: FreeCompanyEstateModel,
    @Json(name = "Formed")
    val formed: Long,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Rank")
    val rank: Int,
    @Json(name = "Slogan")
    val slogan: String,
    @Json(name = "Tag")
    val tag: String
)