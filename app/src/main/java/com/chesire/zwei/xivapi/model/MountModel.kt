package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MountModel(
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Icon")
    val icon: String,
    @Json(name = "IconSmall")
    val iconSmall: String,
    @Json(name = "Order")
    val order: Int,
    @Json(name = "Url")
    val url: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Description")
    val description: String,
    @Json(name = "DescriptionEnhanced")
    val descriptionEnhanced: String,
    @Json(name = "Tooltip")
    val tooltip: String
)