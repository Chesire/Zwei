package com.chesire.zwei.dagger.components

import android.app.Application
import com.chesire.zwei.MockApplication
import com.chesire.zwei.dagger.modules.ActivityModule
import com.chesire.zwei.dagger.modules.ApplicationModule
import com.chesire.zwei.dagger.modules.FragmentModule
import com.chesire.zwei.dagger.modules.MockDatabaseModule
import com.chesire.zwei.dagger.modules.MockServerModule
import com.chesire.zwei.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (ActivityModule::class),
        (AndroidInjectionModule::class),
        (AndroidSupportInjectionModule::class),
        (ApplicationModule::class),
        (FragmentModule::class),
        (MockDatabaseModule::class),
        (MockServerModule::class),
        (ViewModelModule::class)
    ]
)
interface MockComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MockComponent
    }

    fun inject(mockApp: MockApplication)
}