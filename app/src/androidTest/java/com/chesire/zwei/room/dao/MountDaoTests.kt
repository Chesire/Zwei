package com.chesire.zwei.room.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.model.MountModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MountDaoTests {
    private lateinit var db: ZweiDatabase

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            ZweiDatabase::class.java
        ).build()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertSingleMountSavesData() {
        val newModel = generateMountModel(0)
        db.mountDao().insert(newModel)

        Assert.assertTrue(db.mountDao().getAll().contains(newModel))
    }

    @Test
    fun insertMultipleMountsSavesData() {
        val models = listOf(
            generateMountModel(0),
            generateMountModel(1),
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)

        Assert.assertEquals(models, db.mountDao().getAll())
    }

    @Test
    fun findByIdWithNoMatchingMountsReturnsNull() {
        Assert.assertNull(db.mountDao().findById(0))
    }

    @Test
    fun findByIdWithMatchingMountsReturnsMatching() {
        val expectedModel = generateMountModel(1)
        val models = listOf(
            generateMountModel(0),
            expectedModel,
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)

        Assert.assertEquals(expectedModel, db.mountDao().findById(1))
    }

    @Test
    fun getAllWithNoItemsReturnsEmptyList() {
        Assert.assertTrue(db.mountDao().getAll().isEmpty())
    }

    @Test
    fun getAllWithIdsNoItemsReturnsEmptyList() {
        Assert.assertTrue(db.mountDao().getAllByIds(listOf(0, 1, 2)).isEmpty())
    }

    @Test
    fun getAllWithIdsPartialMatchReturnsMatching() {
        val models = listOf(
            generateMountModel(0),
            generateMountModel(1),
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)
        Assert.assertTrue(db.mountDao().getAllByIds(listOf(0, 1, 3)).count() == 2)
    }

    @Test
    fun getAllWithIdsReturnsAllMatching() {
        val models = listOf(
            generateMountModel(0),
            generateMountModel(1),
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)
        Assert.assertEquals(models, db.mountDao().getAllByIds(listOf(0, 1, 2)))
    }

    private fun generateMountModel(
        id: Int,
        icon: String = "icon",
        iconSmall: String = "iconSmall",
        order: Int = 0,
        url: String = "url",
        name: String = "name",
        description: String = "description",
        descriptionEnhanced: String = "description",
        tooltip: String = "tooltip"
    ): MountModel {
        return MountModel(
            id,
            icon,
            iconSmall,
            order,
            url,
            name,
            description,
            descriptionEnhanced,
            tooltip
        )
    }
}