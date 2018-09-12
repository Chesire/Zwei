package com.chesire.zwei.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chesire.zwei.xivapi.model.FreeCompanyModel

@Dao
interface FreeCompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: FreeCompanyModel)

    @Query("SELECT * FROM freecompanymodel LIMIT 1")
    fun get(): FreeCompanyModel?

    @Delete
    fun delete(item: FreeCompanyModel)
}