package com.chesire.zwei.dagger.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.chesire.zwei.OpenForTesting
import com.chesire.zwei.util.PrefHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@OpenForTesting
@Module
class MockApplicationModule {
    //@Provides
    //fun provideApplicationContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

    @Provides
    @Singleton
    fun providePrefHelper(sharedPref: SharedPreferences): PrefHelper {
        return PrefHelper(sharedPref)
    }
}