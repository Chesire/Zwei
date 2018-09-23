package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.PrimingActivity
import com.chesire.zwei.view.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributesPrimingActivity(): PrimingActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributesOnboardingActivity(): OnboardingActivity
}