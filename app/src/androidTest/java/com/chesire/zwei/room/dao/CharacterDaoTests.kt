package com.chesire.zwei.room.dao

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.chesire.zwei.room.ZweiDatabase
import com.chesire.zwei.xivapi.flags.Gender
import com.chesire.zwei.xivapi.flags.Race
import com.chesire.zwei.xivapi.model.CharacterModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDaoTests {
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
    fun insertSingleCharacterSavesData() {
        val newModel = generateCharacterModel()
        db.characterDao().insert(newModel)

        Assert.assertEquals(newModel, db.characterDao().get())
    }

    @Test
    fun insertingMultipleCharactersWithSameIdSavesLastOne() {
        val newModel1 = generateCharacterModel(0)
        val newModel2 = generateCharacterModel(0)

        db.characterDao().insert(newModel1)
        Assert.assertEquals(newModel1, db.characterDao().get())

        db.characterDao().insert(newModel2)
        Assert.assertEquals(newModel2, db.characterDao().get())
    }

    @Test
    fun retrievingCharacterWithoutSettingReturnsNull() {
        Assert.assertNull(db.characterDao().get())
    }

    @Test
    fun deletingCharacterRemovesData() {
        val newModel = generateCharacterModel()
        db.characterDao().insert(newModel)

        Assert.assertEquals(newModel, db.characterDao().get())
        db.characterDao().delete(newModel)
        Assert.assertNull(db.characterDao().get())
    }

    private fun generateCharacterModel(id: Int = 0): CharacterModel {
        return CharacterModel(
            // emptyClass,
            "avatar",
            "bio",
            // classes,
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