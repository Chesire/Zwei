package com.chesire.zwei.dagger.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.chesire.zwei.OpenForTesting
import com.chesire.zwei.util.PrefHelper
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Suppress("unused")
@OpenForTesting
@Module
class MockApplicationModule {
    @Provides
    fun provideApplicationContext(app: Application): Context = app.applicationContext

    @Provides
    @Reusable
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

    @Provides
    @Reusable
    fun providePrefHelper(app: Application, sharedPref: SharedPreferences): PrefHelper {
        return PrefHelper(app, sharedPref)
    }
}
