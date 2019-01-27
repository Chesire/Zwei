package com.chesire.zwei.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.view.SingleLiveEvent
import com.chesire.zwei.view.onboarding.character.GetCharacterStatus
import com.chesire.zwei.xivapi.Status
import com.chesire.zwei.xivapi.XIVApi
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.InfoModel
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val xivApi: XIVApi
) : ViewModel() {
    private val _searchStatus = SingleLiveEvent<Status>()
    private val _getCharacterStatus = SingleLiveEvent<GetCharacterStatus>()
    private val _foundCharacters = MutableLiveData<List<SearchCharacterModel>>()

    val characterName = MutableLiveData<String>()
    val world = MutableLiveData<String>()
    val currentCharacter = MutableLiveData<SearchCharacterModel>()
    val searchStatus: LiveData<Status>
        get() = _searchStatus
    val getCharacterStatus: LiveData<GetCharacterStatus>
        get() = _getCharacterStatus
    val foundCharacters: LiveData<List<SearchCharacterModel>>
        get() = _foundCharacters

    fun searchForCharacter() = launch {
        if (characterName.value == null || world.value == null) {
            _searchStatus.value = Status.Error
            return@launch
        }

        _searchStatus.value = Status.Loading

        try {
            val searchRequest = xivApi.searchForCharacter(characterName.value!!, world.value!!)
            val result = searchRequest.await()

            when (result.status) {
                Status.Error -> _searchStatus.value = Status.Error
                Status.Success -> if (result.data.isEmpty()) {
                    Timber.d("Could not find character - failure no data")
                    _searchStatus.value = Status.Error
                } else {
                    Timber.d("Found ${result.data.count()} characters")
                    _searchStatus.value = Status.Success
                    _foundCharacters.value = result.data
                    currentCharacter.value = result.data.first()
                }
                else -> {
                    Timber.w("Got unexpected branch ${result.status}")
                    _searchStatus.value = Status.Error
                }
            }
        } catch (e: TimeoutException) {
            Timber.e("Operation timeout $e")
            _searchStatus.value = Status.Error
        }
    }

    @Suppress("OptionalWhenBraces")
    fun getCharacter() = launch {
        if (currentCharacter.value == null) {
            _getCharacterStatus.value = GetCharacterStatus.Error
            return@launch
        }

        _getCharacterStatus.value = GetCharacterStatus.Loading

        try {
            val getRequest = xivApi.getCharacter(currentCharacter.value!!.id)
            val result = getRequest.await()

            when {
                result.status == Status.Error -> {
                    _getCharacterStatus.value = GetCharacterStatus.Error
                }
                result.status == Status.Success && result.data is InfoModel -> {
                    _getCharacterStatus.value = GetCharacterStatus.GotInfo
                }
                result.status == Status.Success && result.data is CharacterDetailModel -> {
                    // save to the DB / Repository
                    _getCharacterStatus.value = GetCharacterStatus.GotCharacter
                }
                else -> {
                    Timber.w("Got unexpected branch ${result.status} with data type ${result.data.javaClass}")
                    _getCharacterStatus.value = GetCharacterStatus.Error
                }
            }
        } catch (e: TimeoutException) {
            Timber.e("Operation timeout $e")
            _getCharacterStatus.value = GetCharacterStatus.Error
        }
    }
}
