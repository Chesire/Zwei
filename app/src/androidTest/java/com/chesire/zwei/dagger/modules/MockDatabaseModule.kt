package com.chesire.zwei.dagger.modules

import android.content.Context
import androidx.room.Room
import com.chesire.zwei.room.ZweiDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
internal class MockDatabaseModule : DatabaseModule() {
    @Singleton
    @Provides
    override fun provideDatabase(context: Context): ZweiDatabase {
        return Room
            .inMemoryDatabaseBuilder(context, ZweiDatabase::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }
}