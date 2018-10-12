package com.chesire.zwei.xivapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chesire.zwei.xivapi.flags.Gender
import com.chesire.zwei.xivapi.flags.Race

@Entity
data class CharacterDetailModel(
    // val activeClass: ClassModel,
    val achievements: List<Int>,
    val avatar: String,
    val bio: String,
    // val classes: ClassesModel,
    val gender: Gender,
    @PrimaryKey
    val id: Int,
    val minions: List<Int>,
    val mounts: List<Int>,
    val name: String,
    val portrait: String,
    val race: Race,
    val server: String,
    val title: Int
)
