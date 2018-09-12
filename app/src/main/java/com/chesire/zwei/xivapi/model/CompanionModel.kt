package com.chesire.zwei.xivapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class CompanionModel(
    @PrimaryKey
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Icon")
    val icon: String,
    @Json(name = "IconID")
    val iconId: Int,
    @Json(name = "IconSmall")
    val iconSmall: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Url")
    val url: String,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Tooltip")
    val tooltip: String
)