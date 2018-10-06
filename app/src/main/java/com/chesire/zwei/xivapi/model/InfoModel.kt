package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoModel(
    @Json(name = "Achievements")
    val achievements: StateModel,
    @Json(name = "Character")
    val character: StateModel,
    @Json(name = "FreeCompany")
    val freeCompany: StateModel,
    @Json(name = "FreeCompanyMembers")
    val freeCompanyMembers: StateModel,
    @Json(name = "Friends")
    val friends: StateModel
)
