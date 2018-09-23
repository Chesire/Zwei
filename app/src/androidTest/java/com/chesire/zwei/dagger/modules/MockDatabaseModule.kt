package com.chesire.zwei.dagger.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.chesire.zwei.room.ZweiDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
internal class MockDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): ZweiDatabase {
        return Room
            .inMemoryDatabaseBuilder(context, ZweiDatabase::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(db: ZweiDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideCompanionDao(db: ZweiDatabase) = db.companionDao()

    @Singleton
    @Provides
    fun provideFreeCompanyDao(db: ZweiDatabase) = db.freeCompanyDao()

    @Singleton
    @Provides
    fun provideMountDao(db: ZweiDatabase) = db.mountDao()

    @Singleton
    @Provides
    fun provideTitleDao(db: ZweiDatabase) = db.titleDao()
}