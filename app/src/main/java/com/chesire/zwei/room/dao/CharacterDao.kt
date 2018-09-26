package com.chesire.zwei.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chesire.zwei.xivapi.model.CharacterModel

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CharacterModel)

    @Query("SELECT * FROM charactermodel LIMIT 1")
    fun get(): CharacterModel?

    @Delete
    fun delete(item: CharacterModel)
}