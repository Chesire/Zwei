package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.Race
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

@Suppress("unused")
class RaceAdapter {
    @ToJson
    fun toJson(race: Race): Int = race.value

    @FromJson
    fun fromJson(value: Int): Race {
        return Race.values()
            .find { it.value == value }
            ?: throw JsonDataException("Unexpected Race: $value")
    }
}
