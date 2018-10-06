package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.Gender
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

@Suppress("unused")
class GenderAdapter {
    @ToJson
    fun toJson(gender: Gender): Int = gender.value

    @FromJson
    fun fromJson(value: Int): Gender {
        return Gender.values()
            .find { it.value == value }
            ?: throw JsonDataException("Unexpected Gender: $value")
    }
}
