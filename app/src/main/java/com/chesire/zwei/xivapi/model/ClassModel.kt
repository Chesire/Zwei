package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassModel(
    @Json(name = "ClassID")
    val classId: Int,
    @Json(name = "JobID")
    val jobId: Int,
    @Json(name = "Level")
    val level: Int
)
