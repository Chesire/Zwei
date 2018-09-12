package com.chesire.zwei.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chesire.zwei.xivapi.model.CompanionModel

@Dao
interface CompanionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CompanionModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<CompanionModel>)

    @Query("SELECT * FROM companionmodel WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): CompanionModel?

    @Query("SELECT * FROM companionmodel")
    fun getAll(): List<CompanionModel>

    @Query("SELECT * FROM companionmodel WHERE id IN (:ids)")
    fun getAllByIds(ids: List<Int>): List<CompanionModel>
}