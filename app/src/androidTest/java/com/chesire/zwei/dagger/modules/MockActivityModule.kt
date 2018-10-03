package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.PrimingActivity
import com.chesire.zwei.view.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MockActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributesPrimingActivity(): PrimingActivity

    @ContributesAndroidInjector
    internal abstract fun contributesOnboardingActivity(): OnboardingActivity
}