package com.chesire.zwei.room.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.flags.Gender
import com.chesire.zwei.xivapi.flags.Race
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDaoTests {
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
    fun insertSingleCharacterSavesData() = runBlocking {
        val newModel = generateCharacterModel()
        db.characterDao().insert(newModel)

        assertEquals(newModel, db.characterDao().get())
    }

    @Test
    fun insertingMultipleCharactersWithSameIdSavesLastOne() = runBlocking {
        val newModel1 = generateCharacterModel(0)
        val newModel2 = generateCharacterModel(0)

        db.characterDao().insert(newModel1)
        assertEquals(newModel1, db.characterDao().get())

        db.characterDao().insert(newModel2)
        assertEquals(newModel2, db.characterDao().get())
    }

    @Test
    fun retrievingCharacterWithoutSettingReturnsNull() = runBlocking {
        assertNull(db.characterDao().get())
    }

    @Test
    fun deletingCharacterRemovesData() = runBlocking {
        val newModel = generateCharacterModel()
        db.characterDao().insert(newModel)

        assertEquals(newModel, db.characterDao().get())
        db.characterDao().delete(newModel)
        assertNull(db.characterDao().get())
    }

    private fun generateCharacterModel(id: Int = 0): CharacterDetailModel {
        return CharacterDetailModel(
            emptyList(),
            "avatar",
            "bio",
            Gender.Female,
            id,
            emptyList(),
            emptyList(),
            "name",
            "portrait",
            Race.Miqote,
            "server",
            0
        )
    }
}
