package com.chesire.zwei.xivapi.model

import com.chesire.zwei.xivapi.flags.State
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StateModel(
    @Json(name = "State")
    val state: State,
    // This looks it can be a Bool, an Int, or null
    @Json(name = "Updated")
    val updated: Any?
)
