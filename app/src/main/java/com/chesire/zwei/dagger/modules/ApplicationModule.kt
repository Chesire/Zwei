package com.chesire.zwei.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
internal class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: Application): Context = app.applicationContext
}