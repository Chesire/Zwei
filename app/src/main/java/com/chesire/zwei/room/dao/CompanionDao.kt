package com.chesire.zwei.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chesire.zwei.xivapi.model.CompanionModel

@Dao
interface CompanionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CompanionModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CompanionModel>)

    @Query("SELECT * FROM companionmodel WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Int): CompanionModel?

    @Query("SELECT * FROM companionmodel")
    suspend fun getAll(): List<CompanionModel>

    @Query("SELECT * FROM companionmodel WHERE id IN (:ids)")
    suspend fun getAllByIds(ids: List<Int>): List<CompanionModel>
}
