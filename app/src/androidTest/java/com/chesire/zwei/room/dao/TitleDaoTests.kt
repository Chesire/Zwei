package com.chesire.zwei.room.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.model.TitleModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TitleDaoTests {
    private lateinit var db: ZweiDatabase

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
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

        assertTrue(db.titleDao().getAll().contains(newModel))
    }

    @Test
    fun insertMultipleTitlesSavesData() {
        val models = listOf(
            generateTitleModel(0),
            generateTitleModel(1),
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)

        assertEquals(models, db.titleDao().getAll())
    }

    @Test
    fun findByIdWithNoMatchingTitlesReturnsNull() {
        assertNull(db.titleDao().findById(0))
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

        assertEquals(expectedModel, db.titleDao().findById(1))
    }

    @Test
    fun getAllWithNoItemsReturnsEmptyList() {
        assertTrue(db.titleDao().getAll().isEmpty())
    }

    @Test
    fun getAllWithIdsNoItemsReturnsEmptyList() {
        assertTrue(db.titleDao().getAllByIds(listOf(0, 1, 2)).isEmpty())
    }

    @Test
    fun getAllWithIdsPartialMatchReturnsMatching() {
        val models = listOf(
            generateTitleModel(0),
            generateTitleModel(1),
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)
        assertTrue(db.titleDao().getAllByIds(listOf(0, 1, 3)).count() == 2)
    }

    @Test
    fun getAllWithIdsReturnsAllMatching() {
        val models = listOf(
            generateTitleModel(0),
            generateTitleModel(1),
            generateTitleModel(2)
        )
        db.titleDao().insertAll(models)
        assertEquals(models, db.titleDao().getAllByIds(listOf(0, 1, 2)))
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
