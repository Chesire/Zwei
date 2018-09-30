package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.onboarding.EnterWorldFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributesEnterWorldFragment(): EnterWorldFragment
}