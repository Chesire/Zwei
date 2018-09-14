package com.chesire.zwei.room.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
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

    private fun generateCharacterModel(id: Int = 0): CharacterModel {
        return CharacterModel(
            //emptyClass,
            "avatar",
            "bio",
            //classes,
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