package com.chesire.zwei.xivapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClassesModel(
    // Disciples of War/Magic
    @Json(name = "1_19")
    val Paladin: ClassModel,
    @Json(name = "2_20")
    val Monk: ClassModel,
    @Json(name = "3_21")
    val Warrior: ClassModel,
    @Json(name = "4_22")
    val Dragoon: ClassModel,
    @Json(name = "5_23")
    val Bard: ClassModel,
    @Json(name = "6_24")
    val WhiteMage: ClassModel,
    @Json(name = "7_25")
    val BlackMage: ClassModel,
    @Json(name = "26_27")
    val Summoner: ClassModel,
    @Json(name = "26_28")
    val Scholar: ClassModel,
    @Json(name = "29_30")
    val Ninja: ClassModel,
    @Json(name = "31_31")
    val Machinist: ClassModel,
    @Json(name = "32_32")
    val DarkKnight: ClassModel,
    @Json(name = "33_33")
    val Astrologian: ClassModel,
    @Json(name = "34_34")
    val Samurai: ClassModel,
    @Json(name = "35_35")
    val RedMage: ClassModel,

    // Disciples of the Hand
    @Json(name = "8_8")
    val Carpenter: ClassModel,
    @Json(name = "9_9")
    val Blacksmith: ClassModel,
    @Json(name = "10_10")
    val Armorer: ClassModel,
    @Json(name = "11_11")
    val Goldsmith: ClassModel,
    @Json(name = "12_12")
    val Leatherworker: ClassModel,
    @Json(name = "13_13")
    val Weaver: ClassModel,
    @Json(name = "14_14")
    val Alchemist: ClassModel,
    @Json(name = "15_15")
    val Culinarian: ClassModel,

    // Disciples of the Land
    @Json(name = "16_16")
    val Miner: ClassModel,
    @Json(name = "17_17")
    val Botanist: ClassModel,
    @Json(name = "18_18")
    val Fisher: ClassModel
)
