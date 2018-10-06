package com.chesire.zwei.xivapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class TitleModel(
    @PrimaryKey
    @Json(name = "ID")
    val id: Int,
    @Json(name = "IsPrefix")
    val isPrefix: Int,
    @Json(name = "NameFemale")
    val nameFemale: String,
    @Json(name = "Name")
    val nameMale: String
)
