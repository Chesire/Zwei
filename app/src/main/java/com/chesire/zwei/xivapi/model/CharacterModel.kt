package com.chesire.zwei.xivapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chesire.zwei.xivapi.flags.Gender
import com.chesire.zwei.xivapi.flags.Race
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class CharacterModel(
    // @Json(name = "ActiveClassJob")
    // val activeClass: ClassModel,
    @Json(name = "Avatar")
    val avatar: String,
    @Json(name = "Bio")
    val bio: String,
    // @Json(name = "ClassJobs")
    // val classes: ClassesModel,
    @Json(name = "Gender")
    val gender: Gender,
    @PrimaryKey
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Minions")
    val minions: List<Int>,
    @Json(name = "Mounts")
    val mounts: List<Int>,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Portrait")
    val portrait: String,
    @Json(name = "Race")
    val race: Race,
    @Json(name = "Server")
    val server: String,
    @Json(name = "Title")
    val title: Int
)
