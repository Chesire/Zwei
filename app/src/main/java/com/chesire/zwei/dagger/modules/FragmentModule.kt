package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.onboarding.SelectWorldFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributesSelectWorldFragment(): SelectWorldFragment
}