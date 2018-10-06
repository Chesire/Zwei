package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.AchievementsModel
import com.chesire.zwei.xivapi.model.CharacterModel
import com.chesire.zwei.xivapi.model.FreeCompanyMemberModel
import com.chesire.zwei.xivapi.model.FreeCompanyModel
import com.chesire.zwei.xivapi.model.InfoModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCharacterResponse(
    @Json(name = "Achievements")
    val achievements: AchievementsModel?,
    @Json(name = "Character")
    val character: CharacterModel?,
    @Json(name = "FreeCompany")
    val freeCompany: FreeCompanyModel?,
    @Json(name = "FreeCompanyMembers")
    val freeCompanyMembers: List<FreeCompanyMemberModel>?,
    @Json(name = "Info")
    val info: InfoModel
)
