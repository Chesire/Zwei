package com.chesire.zwei.room

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.chesire.zwei.xivapi.model.TitleModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TitleDaoTests {
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
    fun insertSingleTitleSavesData() {
        val newModel = generateTitleModel(0)
        db.titleDao().insert(newModel)

        Assert.assertTrue(db.titleDao().getAll().contains(newModel))
    }

    @Test
    fun insertMultipleTitlesSavesData() {
        val models = listOf(
            generateTitleModel(0),
            generateTitleModel(1),
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)

        Assert.assertEquals(models, db.titleDao().getAll())
    }

    @Test
    fun findByIdWithNoMatchingTitlesReturnsNull() {
        Assert.assertNull(db.titleDao().findById(0))
    }

    @Test
    fun findByIdWithMatchingTitlesReturnsMatching() {
        val expectedModel = generateTitleModel(1)
        val models = listOf(
            generateTitleModel(0),
            expectedModel,
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)

        Assert.assertEquals(expectedModel, db.titleDao().findById(1))
    }

    @Test
    fun getAllWithNoItemsReturnsEmptyList() {
        Assert.assertTrue(db.titleDao().getAll().isEmpty())
    }

    @Test
    fun getAllWithIdsNoItemsReturnsEmptyList() {
        Assert.assertTrue(db.titleDao().getAllByIds(listOf(0, 1, 2)).isEmpty())
    }

    @Test
    fun getAllWithIdsPartialMatchReturnsMatching() {
        val models = listOf(
            generateTitleModel(0),
            generateTitleModel(1),
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)
        Assert.assertTrue(db.titleDao().getAllByIds(listOf(0, 1, 3)).count() == 2)
    }

    @Test
    fun getAllWithIdsReturnsAllMatching() {
        val models = listOf(
            generateTitleModel(0),
            generateTitleModel(1),
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)
        Assert.assertEquals(models, db.titleDao().getAllByIds(listOf(0, 1, 2)))
    }

    private fun generateTitleModel(
        id: Int,
        isPrefix: Int = 0,
        nameFemale: String = "nameFemale",
        nameMale: String = "nameMale"
    ): TitleModel {
        return TitleModel(id, isPrefix, nameFemale, nameMale)
    }
}
