package com.chesire.zwei.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chesire.zwei.xivapi.model.CharacterDetailModel

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CharacterDetailModel)

    @Query("SELECT * FROM characterdetailmodel LIMIT 1")
    suspend fun get(): CharacterDetailModel?

    @Delete
    suspend fun delete(item: CharacterDetailModel)
}
