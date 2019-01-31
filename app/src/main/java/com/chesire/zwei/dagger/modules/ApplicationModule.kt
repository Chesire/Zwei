package com.chesire.zwei.dagger.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Suppress("unused")
@Module
class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: Application): Context = app.applicationContext

    @Suppress("ExpressionBodySyntax")
    @Provides
    @Reusable
    fun provideSharedPrefs(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }
}
