package com.chesire.zwei.room.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.model.FreeCompanyEstateModel
import com.chesire.zwei.xivapi.model.FreeCompanyModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FreeCompanyDaoTests {
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
    fun insertFreeCompanySavesData() {
        val newModel = generateFreeCompanyModel(0)
        db.freeCompanyDao().insert(newModel)

        assertEquals(newModel, db.freeCompanyDao().get())
    }

    @Test
    fun insertingMultipleFreeCompanySavesLastOne() {
        val newModel1 = generateFreeCompanyModel(0)
        val newModel2 = generateFreeCompanyModel(0)

        db.freeCompanyDao().insert(newModel1)
        assertEquals(newModel1, db.freeCompanyDao().get())

        db.freeCompanyDao().insert(newModel2)
        assertEquals(newModel2, db.freeCompanyDao().get())
    }

    @Test
    fun retrievingFreeCompanyWithoutSettingReturnsNull() {
        assertNull(db.freeCompanyDao().get())
    }

    @Test
    fun deletingFreeCompanyRemovesData() {
        val newModel = generateFreeCompanyModel(0)
        db.freeCompanyDao().insert(newModel)

        assertEquals(newModel, db.freeCompanyDao().get())
        db.freeCompanyDao().delete(newModel)
        assertNull(db.freeCompanyDao().get())
    }

    private fun generateFreeCompanyModel(id: Int): FreeCompanyModel {
        return FreeCompanyModel(
            id,
            0,
            listOf(),
            FreeCompanyEstateModel(
                "greetings",
                "name",
                "plot"
            ),
            0,
            "name",
            0,
            "slogan",
            "tag"
        )
    }
}
