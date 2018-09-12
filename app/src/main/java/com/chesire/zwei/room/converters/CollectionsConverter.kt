package com.chesire.zwei.room.converters

import android.arch.persistence.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class CollectionsConverter {
    private val moshi = Moshi.Builder().build()
    private val listOfStringAdapter: JsonAdapter<List<String>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, String::class.java))

    @TypeConverter
    fun fromString(value: String): List<String>? {
        return listOfStringAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromListOfString(value: List<String>): String {
        return listOfStringAdapter.toJson(value)
    }
}