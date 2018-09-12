package com.chesire.zwei.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chesire.zwei.xivapi.model.CompanionModel

@Database(
    entities = [(CompanionModel::class)],
    version = 1
)
abstract class ZweiDatabase : RoomDatabase() {
    abstract fun companionDao(): CompanionDao
}