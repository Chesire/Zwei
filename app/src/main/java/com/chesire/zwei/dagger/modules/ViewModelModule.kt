package com.chesire.zwei.dagger.modules

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import dagger.Module
import kotlin.reflect.KClass

@Suppress("unused")
@Module
abstract class ViewModelModule {
    //@Binds
    //@IntoMap
    //@ViewModelKey(type:class)
    //internal abstract fun bind*ViewModel(viewModel: type): ViewModel

    @MustBeDocumented
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    private annotation class ViewModelKey(val value: KClass<out ViewModel>)
}