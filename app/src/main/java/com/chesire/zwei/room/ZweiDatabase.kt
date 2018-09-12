package com.chesire.zwei.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chesire.zwei.xivapi.model.CompanionModel
import com.chesire.zwei.xivapi.model.MountModel
import com.chesire.zwei.xivapi.model.TitleModel

@Database(
    entities = [(CompanionModel::class), (MountModel::class), (TitleModel::class)],
    version = 1
)
abstract class ZweiDatabase : RoomDatabase() {
    abstract fun companionDao(): CompanionDao
    abstract fun mountDao(): MountDao
    abstract fun titleDao(): TitleDao
}