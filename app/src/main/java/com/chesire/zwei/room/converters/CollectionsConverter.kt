package com.chesire.zwei.room.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class CollectionsConverter {
    private val moshi = Moshi.Builder().build()
    private val listOfStringAdapter: JsonAdapter<List<String>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, String::class.java))
    private val listOfIntAdapter: JsonAdapter<List<Int>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, Integer::class.java))

    @TypeConverter
    fun fromStringToListOfString(value: String): List<String>? = listOfStringAdapter.fromJson(value)

    @TypeConverter
    fun fromListOfStringToString(value: List<String>): String = listOfStringAdapter.toJson(value)

    @TypeConverter
    fun fromStringToListOfInt(value: String): List<Int>? = listOfIntAdapter.fromJson(value)

    @TypeConverter
    fun fromListOfIntToString(value: List<Int>): String = listOfIntAdapter.toJson(value)
}