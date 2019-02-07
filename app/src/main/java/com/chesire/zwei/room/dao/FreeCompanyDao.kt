package com.chesire.zwei.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chesire.zwei.xivapi.model.FreeCompanyModel

@Dao
interface FreeCompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FreeCompanyModel)

    @Query("SELECT * FROM freecompanymodel LIMIT 1")
    suspend fun get(): FreeCompanyModel?

    @Delete
    suspend fun delete(item: FreeCompanyModel)
}
