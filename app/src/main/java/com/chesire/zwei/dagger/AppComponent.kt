package com.chesire.zwei.dagger

import android.app.Application
import com.chesire.zwei.ZweiApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(zweiApp: ZweiApplication)
}