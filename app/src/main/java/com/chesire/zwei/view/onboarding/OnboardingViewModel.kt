package com.chesire.zwei.view.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.xivapi.XIVApi
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val xivApi: XIVApi
) : ViewModel() {
    val characterName: MutableLiveData<String> = MutableLiveData()
    val world: MutableLiveData<String> = MutableLiveData()

}