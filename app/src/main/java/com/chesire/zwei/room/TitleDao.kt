package com.chesire.zwei.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chesire.zwei.xivapi.model.TitleModel

@Dao
interface TitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TitleModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<TitleModel>)

    @Query("SELECT * FROM titlemodel WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): TitleModel?

    @Query("SELECT * FROM titlemodel")
    fun getAll(): List<TitleModel>

    @Query("SELECT * FROM titlemodel WHERE id IN (:ids)")
    fun getAllByIds(ids: List<Int>): List<TitleModel>
}