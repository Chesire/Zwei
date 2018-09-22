package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.onboarding.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributesWelcomeFragment(): WelcomeFragment
}