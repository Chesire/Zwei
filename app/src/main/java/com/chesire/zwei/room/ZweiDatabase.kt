package com.chesire.zwei.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.chesire.zwei.room.converters.CollectionsConverter
import com.chesire.zwei.room.converters.FreeCompanyEstateModelConverter
import com.chesire.zwei.room.dao.CompanionDao
import com.chesire.zwei.room.dao.FreeCompanyDao
import com.chesire.zwei.room.dao.MountDao
import com.chesire.zwei.room.dao.TitleDao
import com.chesire.zwei.xivapi.model.CompanionModel
import com.chesire.zwei.xivapi.model.FreeCompanyModel
import com.chesire.zwei.xivapi.model.MountModel
import com.chesire.zwei.xivapi.model.TitleModel

@Database(
    entities = [(CompanionModel::class), (MountModel::class), (TitleModel::class), (FreeCompanyModel::class)],
    version = 1
)
@TypeConverters(FreeCompanyEstateModelConverter::class, CollectionsConverter::class)
abstract class ZweiDatabase : RoomDatabase() {
    abstract fun companionDao(): CompanionDao
    abstract fun mountDao(): MountDao
    abstract fun titleDao(): TitleDao
    abstract fun freeCompanyDao(): FreeCompanyDao
}