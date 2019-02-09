package com.chesire.zwei.dagger.modules

import com.chesire.zwei.util.IOContext
import com.chesire.zwei.util.MainContext
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
internal class CoroutinesModule {
    @IOContext
    @Provides
    fun providesIOCoroutineContext(): CoroutineContext = Dispatchers.IO

    @MainContext
    @Provides
    fun providesMainCoroutineContext(): CoroutineContext = Dispatchers.Main
}
