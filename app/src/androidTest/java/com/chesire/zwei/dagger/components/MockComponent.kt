package com.chesire.zwei.dagger.components

import android.app.Application
import com.chesire.zwei.MockApplication
import com.chesire.zwei.dagger.modules.ActivityModule
import com.chesire.zwei.dagger.modules.CoroutinesModule
import com.chesire.zwei.dagger.modules.FragmentModule
import com.chesire.zwei.dagger.modules.MockApplicationModule
import com.chesire.zwei.dagger.modules.MockDatabaseModule
import com.chesire.zwei.dagger.modules.MockServerModule
import com.chesire.zwei.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        AndroidSupportInjectionModule::class,
        CoroutinesModule::class,
        FragmentModule::class,
        MockApplicationModule::class,
        MockDatabaseModule::class,
        MockServerModule::class,
        ViewModelModule::class
    ]
)
interface MockComponent : AndroidInjector<MockApplication> {
    @Component.Builder
    interface Builder {
        fun mockApplicationModule(mockApplicationModule: MockApplicationModule): Builder

        @BindsInstance
        fun create(application: Application): Builder

        fun build(): MockComponent
    }
}
