package com.chesire.zwei.dagger.components

import android.app.Application
import com.chesire.zwei.ZweiApplication
import com.chesire.zwei.dagger.modules.ActivityModule
import com.chesire.zwei.dagger.modules.ApplicationModule
import com.chesire.zwei.dagger.modules.CoroutinesModule
import com.chesire.zwei.dagger.modules.DatabaseModule
import com.chesire.zwei.dagger.modules.FragmentModule
import com.chesire.zwei.dagger.modules.ServerModule
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
        ApplicationModule::class,
        CoroutinesModule::class,
        DatabaseModule::class,
        FragmentModule::class,
        ServerModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<ZweiApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }
}
