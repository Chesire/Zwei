package com.chesire.zwei.xivapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class FreeCompanyModel(
    @PrimaryKey
    val id: Int = 0, // the id should be kept 0, as we never want more than one
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