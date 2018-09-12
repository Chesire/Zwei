package com.chesire.zwei.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chesire.zwei.xivapi.model.MountModel

@Dao
interface MountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: MountModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<MountModel>)

    @Query("SELECT * FROM mountmodel WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): MountModel?

    @Query("SELECT * FROM mountmodel")
    fun getAll(): List<MountModel>

    @Query("SELECT * FROM mountmodel WHERE id IN (:ids)")
    fun getAllByIds(ids: List<Int>): List<MountModel>
}