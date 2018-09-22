package com.chesire.zwei.dagger

import android.app.Application
import com.chesire.zwei.ZweiApplication
import com.chesire.zwei.dagger.modules.ActivityModule
import com.chesire.zwei.dagger.modules.ApplicationModule
import com.chesire.zwei.dagger.modules.DatabaseModule
import com.chesire.zwei.dagger.modules.FragmentModule
import com.chesire.zwei.dagger.modules.ServerModule
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
        (DatabaseModule::class),
        (FragmentModule::class),
        (ServerModule::class),
        (ViewModelModule::class)
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(zweiApp: ZweiApplication)
}