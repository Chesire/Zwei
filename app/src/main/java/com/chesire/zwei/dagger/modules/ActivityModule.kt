package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.PrimingActivity
import com.chesire.zwei.view.onboarding.OnboardingActivity
import com.chesire.zwei.view.profile.ProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributesPrimingActivity(): PrimingActivity

    @ContributesAndroidInjector
    internal abstract fun contributesOnboardingActivity(): OnboardingActivity

    @ContributesAndroidInjector
    internal abstract fun contributesProfileActivity(): ProfileActivity
}
