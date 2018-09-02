package com.chesire.zwei.xivapi.response

import com.chesire.zwei.xivapi.model.MountModel
import com.chesire.zwei.xivapi.model.PaginationModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GetMountsResponse(
    @Json(name = "pagination")
    val pagination: PaginationModel,
    @Json(name = "results")
    val mounts: List<MountModel>
)