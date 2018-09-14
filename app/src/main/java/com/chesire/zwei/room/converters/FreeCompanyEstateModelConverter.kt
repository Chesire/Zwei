package com.chesire.zwei.room.converters

import android.arch.persistence.room.TypeConverter
import com.chesire.zwei.xivapi.model.FreeCompanyEstateModel
import com.squareup.moshi.Moshi

class FreeCompanyEstateModelConverter {
    private val adapter = Moshi.Builder().build().adapter(FreeCompanyEstateModel::class.java)

    @TypeConverter
    fun fromString(value: String): FreeCompanyEstateModel? = adapter.fromJson(value)

    @TypeConverter
    fun fromFreeCompanyEstateModel(value: FreeCompanyEstateModel): String = adapter.toJson(value)
}