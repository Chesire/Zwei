package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.onboarding.character.isYourCharacter.IsYourCharacterFragment
import com.chesire.zwei.view.onboarding.character.loadingCharacter.LoadingCharacterFragment
import com.chesire.zwei.view.onboarding.initial.request.RequestFragment
import com.chesire.zwei.view.onboarding.search.EnterCharacterFragment
import com.chesire.zwei.view.onboarding.search.EnterWorldFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributesEnterCharacterFragment(): EnterCharacterFragment

    @ContributesAndroidInjector
    internal abstract fun contributesEnterWorldFragment(): EnterWorldFragment

    @ContributesAndroidInjector
    internal abstract fun contributesIsYourCharacterFragment(): IsYourCharacterFragment

    @ContributesAndroidInjector
    internal abstract fun contributesLoadingCharacterFragment(): LoadingCharacterFragment

    @ContributesAndroidInjector
    internal abstract fun contributesRequestFragment(): RequestFragment
}
