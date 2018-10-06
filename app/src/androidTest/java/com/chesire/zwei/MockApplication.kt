package com.chesire.zwei

import com.chesire.zwei.dagger.components.DaggerMockComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@Suppress("Unused")
class MockApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMockComponent
            .builder()
            .create(this)
            .build()
    }
}
