package com.chesire.zwei.dagger.modules

import com.chesire.zwei.view.onboarding.character.ChooseCharacterFragment
import com.chesire.zwei.view.onboarding.character.LoadingCharacterFragment
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
    internal abstract fun contributesChooseCharacterFragment(): ChooseCharacterFragment

    @ContributesAndroidInjector
    internal abstract fun contributesLoadingCharacterFragment(): LoadingCharacterFragment

    @ContributesAndroidInjector
    internal abstract fun contributesRequestFragment(): RequestFragment
}
