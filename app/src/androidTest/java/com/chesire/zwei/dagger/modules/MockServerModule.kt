package com.chesire.zwei.dagger.modules

import com.chesire.zwei.mocks.MockXIVApiService
import com.chesire.zwei.xivapi.XIVApiService
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
class MockServerModule {
    @Provides
    fun providesMockXIVApiService(): XIVApiService {
        return MockXIVApiService()
    }
}