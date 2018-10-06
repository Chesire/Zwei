package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.State
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

@Suppress("unused")
class StateAdapter {
    @ToJson
    fun toJson(state: State): Int = state.value

    @FromJson
    fun fromJson(value: Int): State {
        return State.values()
            .find { it.value == value }
            ?: throw JsonDataException("Unexpected State: $value")
    }
}
