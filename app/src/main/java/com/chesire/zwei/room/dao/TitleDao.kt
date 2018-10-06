package com.chesire.zwei.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
