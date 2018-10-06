package com.chesire.zwei.view.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.xivapi.Status
import com.chesire.zwei.xivapi.XIVApi
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val xivApi: XIVApi
) : ViewModel() {
    val characterName: MutableLiveData<String> = MutableLiveData()
    val world: MutableLiveData<String> = MutableLiveData()

    fun searchForCharacter() = launch {
        val searchRequest = xivApi.searchForCharacter(characterName.value!!, world.value!!)
        val result = searchRequest.await()
        when (result.status) {
            Status.Loading -> {
                // change fragment from the UI on click? Then can perform action during animation?
                // Show loading indicator
            }
            Status.Error -> {
                // Display error to user, act on error
            }
            Status.Success -> {
                // Act on the specific success and go to relevant screen
                if (result.data.isEmpty()) {
                    // display an error
                    Timber.w("Failure no data")
                } else {
                    // mutate some live data to show the new characters list
                    Timber.w("Results - ${result.data.count()}")
                }
            }
        }
    }
}