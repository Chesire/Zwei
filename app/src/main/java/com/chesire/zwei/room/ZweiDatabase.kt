package com.chesire.zwei.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chesire.zwei.room.converters.CollectionsConverter
import com.chesire.zwei.room.converters.FreeCompanyEstateModelConverter
import com.chesire.zwei.room.converters.GenderConverter
import com.chesire.zwei.room.converters.RaceConverter
import com.chesire.zwei.room.dao.CharacterDao
import com.chesire.zwei.room.dao.CompanionDao
import com.chesire.zwei.room.dao.FreeCompanyDao
import com.chesire.zwei.room.dao.MountDao
import com.chesire.zwei.room.dao.TitleDao
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.CompanionModel
import com.chesire.zwei.xivapi.model.FreeCompanyModel
import com.chesire.zwei.xivapi.model.MountModel
import com.chesire.zwei.xivapi.model.TitleModel
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        (CompanionModel::class),
        (MountModel::class),
        (TitleModel::class),
        (FreeCompanyModel::class),
        (CharacterDetailModel::class)
    ],
    version = 1
)
@TypeConverters(
    CollectionsConverter::class,
    FreeCompanyEstateModelConverter::class,
    GenderConverter::class,
    RaceConverter::class
)
abstract class ZweiDatabase : RoomDatabase() {
    abstract fun companionDao(): CompanionDao
    abstract fun mountDao(): MountDao
    abstract fun titleDao(): TitleDao
    abstract fun freeCompanyDao(): FreeCompanyDao
    abstract fun characterDao(): CharacterDao
}
