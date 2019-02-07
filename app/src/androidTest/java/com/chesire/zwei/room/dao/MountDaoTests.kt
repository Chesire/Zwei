package com.chesire.zwei.room.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.model.MountModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MountDaoTests {
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
    fun insertSingleMountSavesData() = runBlocking {
        val newModel = generateMountModel(0)
        db.mountDao().insert(newModel)

        assertTrue(db.mountDao().getAll().contains(newModel))
    }

    @Test
    fun insertMultipleMountsSavesData() = runBlocking {
        val models = listOf(
            generateMountModel(0),
            generateMountModel(1),
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)

        assertEquals(models, db.mountDao().getAll())
    }

    @Test
    fun findByIdWithNoMatchingMountsReturnsNull() = runBlocking {
        assertNull(db.mountDao().findById(0))
    }

    @Test
    fun findByIdWithMatchingMountsReturnsMatching() = runBlocking {
        val expectedModel = generateMountModel(1)
        val models = listOf(
            generateMountModel(0),
            expectedModel,
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)

        assertEquals(expectedModel, db.mountDao().findById(1))
    }

    @Test
    fun getAllWithNoItemsReturnsEmptyList() = runBlocking {
        assertTrue(db.mountDao().getAll().isEmpty())
    }

    @Test
    fun getAllWithIdsNoItemsReturnsEmptyList() = runBlocking {
        assertTrue(db.mountDao().getAllByIds(listOf(0, 1, 2)).isEmpty())
    }

    @Test
    fun getAllWithIdsPartialMatchReturnsMatching() = runBlocking {
        val models = listOf(
            generateMountModel(0),
            generateMountModel(1),
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)
        assertTrue(db.mountDao().getAllByIds(listOf(0, 1, 3)).count() == 2)
    }

    @Test
    fun getAllWithIdsReturnsAllMatching() = runBlocking {
        val models = listOf(
            generateMountModel(0),
            generateMountModel(1),
            generateMountModel(2)
        )
        db.mountDao().insertAll(models)
        assertEquals(models, db.mountDao().getAllByIds(listOf(0, 1, 2)))
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
