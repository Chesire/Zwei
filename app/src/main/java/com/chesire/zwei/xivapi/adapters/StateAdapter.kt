package com.chesire.zwei.xivapi.adapters

import com.chesire.zwei.xivapi.flags.State
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

@Suppress("unused")
class StateAdapter {
    @ToJson
    fun toJson(state: State): String {
        return state.value.toString()
    }

    @FromJson
    fun fromJson(value: Int): State {
        return when (value) {
            0 -> State.None
            1 -> State.Adding
            2 -> State.Cached
            3 -> State.NotFound
            4 -> State.Blacklist
            5 -> State.Private
            else -> throw JsonDataException("Unexpected State: $value")
        }
    }
}