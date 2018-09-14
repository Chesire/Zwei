package com.chesire.zwei.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
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