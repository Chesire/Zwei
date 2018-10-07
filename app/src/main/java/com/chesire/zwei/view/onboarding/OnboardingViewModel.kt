package com.chesire.zwei.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.xivapi.Status
import com.chesire.zwei.xivapi.XIVApi
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val xivApi: XIVApi
) : ViewModel() {
    private val _searchStatus = MutableLiveData<Status>()
    private val _foundCharacters = MutableLiveData<List<SearchCharacterModel>>()

    val characterName = MutableLiveData<String>()
    val world = MutableLiveData<String>()
    val searchStatus: LiveData<Status>
        get() = _searchStatus
    val foundCharacters: LiveData<List<SearchCharacterModel>>
        get() = _foundCharacters

    fun searchForCharacter() = launch(UI) {
        _searchStatus.value = Status.Loading
        val searchRequest = xivApi.searchForCharacter(characterName.value!!, world.value!!)
        val result = searchRequest.await()

        when (result.status) {
            Status.Error -> _searchStatus.value = Status.Error
            Status.Success -> {
                if (result.data.isEmpty()) {
                    Timber.d("Could not find character - failure no data")
                    _searchStatus.value = Status.Error
                } else {
                    Timber.d("Found ${result.data.count()} characters")
                    _searchStatus.value = Status.Success
                    _foundCharacters.value = result.data
                    setCurrentSelectedCharacter(result.data.first())
                }
            }
            else -> {
                Timber.w("Got unexpected branch ${result.status}")
                _searchStatus.value = Status.Error
            }
        }
    }

    fun setCurrentSelectedCharacter(model: SearchCharacterModel) {
        // set current model data, will be displayed to user
        Timber.d("currentSelectedCharacter set to $model")
    }
}
