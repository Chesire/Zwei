package com.chesire.zwei.dagger.modules

import android.content.Context
import androidx.room.Room
import com.chesire.zwei.room.ZweiDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): ZweiDatabase {
        return Room
            .databaseBuilder(context, ZweiDatabase::class.java, "zweidb.db")
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