package com.chesire.zwei.room

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.chesire.zwei.xivapi.model.CompanionModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CompanionDaoTests {
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
    fun insertSingleCompanionSavesData() {
        val newModel = generateCompanionModel(0)
        db.companionDao().insert(newModel)

        Assert.assertTrue(db.companionDao().getAll().contains(newModel))
    }

    @Test
    fun insertMultipleCompanionsSavesData() {
        val models = listOf(
            generateCompanionModel(0),
            generateCompanionModel(1),
            generateCompanionModel(2)
        )
        db.companionDao().insertAll(models)

        Assert.assertEquals(models, db.companionDao().getAll())
    }

    @Test
    fun findByIdWithNoMatchingCompanionsReturnsNull() {
        Assert.assertNull(db.companionDao().findById(0))
    }

    @Test
    fun findByIdWithMatchingCompanionsReturnsMatching() {
        val expectedModel = generateCompanionModel(1)
        val models = listOf(
            generateCompanionModel(0),
            expectedModel,
            generateCompanionModel(2)
        )
        db.companionDao().insertAll(models)

        Assert.assertEquals(expectedModel, db.companionDao().findById(1))
    }

    @Test
    fun getAllWithNoItemsReturnsEmptyList() {
        Assert.assertTrue(db.companionDao().getAll().isEmpty())
    }

    @Test
    fun getAllWithIdsNoItemsReturnsEmptyList() {
        Assert.assertTrue(db.companionDao().getAllByIds(listOf(0, 1, 2)).isEmpty())
    }

    @Test
    fun getAllWithIdsPartialMatchReturnsMatching() {
        val models = listOf(
            generateCompanionModel(0),
            generateCompanionModel(1),
            generateCompanionModel(2)
        )
        db.companionDao().insertAll(models)
        Assert.assertTrue(db.companionDao().getAllByIds(listOf(0, 1, 3)).count() == 2)
    }

    @Test
    fun getAllWithIdsReturnsAllMatching() {
        val models = listOf(
            generateCompanionModel(0),
            generateCompanionModel(1),
            generateCompanionModel(2)
        )
        db.companionDao().insertAll(models)
        Assert.assertEquals(models, db.companionDao().getAllByIds(listOf(0, 1, 2)))
    }

    private fun generateCompanionModel(
        id: Int,
        icon: String = "icon",
        iconId: Int = 0,
        iconSmall: String = "iconSmall",
        name: String = "name",
        url: String = "url",
        description: String = "description",
        tooltip: String = "tooltip"
    ): CompanionModel {
        return CompanionModel(id, icon, iconId, iconSmall, name, url, description, tooltip)
    }
}