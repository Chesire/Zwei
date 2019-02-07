package com.chesire.zwei.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chesire.zwei.xivapi.model.MountModel

@Dao
interface MountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MountModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MountModel>)

    @Query("SELECT * FROM mountmodel WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Int): MountModel?

    @Query("SELECT * FROM mountmodel")
    suspend fun getAll(): List<MountModel>

    @Query("SELECT * FROM mountmodel WHERE id IN (:ids)")
    suspend fun getAllByIds(ids: List<Int>): List<MountModel>
}
