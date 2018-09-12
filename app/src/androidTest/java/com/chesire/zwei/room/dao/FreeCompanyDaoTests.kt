package com.chesire.zwei.room.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.model.FreeCompanyEstateModel
import com.chesire.zwei.xivapi.model.FreeCompanyModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FreeCompanyDaoTests {
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
    fun insertFreeCompanySavesData() {
        val newModel = generateFreeCompanyModel(0)
        db.freeCompanyDao().insert(newModel)

        Assert.assertEquals(newModel, db.freeCompanyDao().get())
    }

    @Test
    fun insertingMultipleFreeCompanySavesLastOne() {
        val newModel1 = generateFreeCompanyModel(0)
        val newModel2 = generateFreeCompanyModel(0)

        db.freeCompanyDao().insert(newModel1)
        Assert.assertEquals(newModel1, db.freeCompanyDao().get())

        db.freeCompanyDao().insert(newModel2)
        Assert.assertEquals(newModel2, db.freeCompanyDao().get())
    }

    @Test
    fun retrievingFreeCompanyWithoutSettingReturnsNull() {
        Assert.assertNull(db.freeCompanyDao().get())
    }

    @Test
    fun deletingFreeCompanyRemovesData() {
        val newModel = generateFreeCompanyModel(0)
        db.freeCompanyDao().insert(newModel)

        Assert.assertEquals(newModel, db.freeCompanyDao().get())
        db.freeCompanyDao().delete(newModel)
        Assert.assertNull(db.freeCompanyDao().get())
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