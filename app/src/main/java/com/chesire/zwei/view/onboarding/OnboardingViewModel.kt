package com.chesire.zwei.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.util.IOContext
import com.chesire.zwei.util.LiveDataStatus
import com.chesire.zwei.util.SingleLiveEvent
import com.chesire.zwei.view.onboarding.character.loadingCharacter.GetCharacterStatus
import com.chesire.zwei.xivapi.Resource
import com.chesire.zwei.xivapi.XIVApi
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class OnboardingViewModel @Inject constructor(
    private val xivApi: XIVApi,
    @IOContext private val ioContext: CoroutineContext
) : ViewModel() {
    private val job = Job()
    private val ioScope = CoroutineScope(job + ioContext)
    private val _searchStatus = SingleLiveEvent<LiveDataStatus>()
    private val _getCharacterStatus = SingleLiveEvent<GetCharacterStatus>()
    private val _foundCharacters = MutableLiveData<List<SearchCharacterModel>>()

    val characterName = MutableLiveData<String>()
    val world = MutableLiveData<String>()
    val currentCharacter = MutableLiveData<SearchCharacterModel>()
    val searchStatus: LiveData<LiveDataStatus>
        get() = _searchStatus
    val getCharacterStatus: LiveData<GetCharacterStatus>
        get() = _getCharacterStatus
    val foundCharacters: LiveData<List<SearchCharacterModel>>
        get() = _foundCharacters

    fun searchForCharacter() {
        if (characterName.value.isNullOrBlank() || world.value.isNullOrBlank()) {
            _searchStatus.value = LiveDataStatus.Error
            return
        }

        _searchStatus.value = LiveDataStatus.Loading
        ioScope.launch {
            try {
                val result = xivApi.searchForCharacter(characterName.value!!, world.value!!)

                when (result) {
                    is Resource.Error -> {
                        Timber.w("Error searching for character - ${result.msg}")
                        _searchStatus.postValue(LiveDataStatus.Error)
                    }
                    is Resource.Success -> if (result.data.isEmpty()) {
                        Timber.d("Could not find character - failure no data")
                        _searchStatus.postValue(LiveDataStatus.Error)
                    } else {
                        Timber.d("Found ${result.data.count()} characters")
                        _searchStatus.postValue(LiveDataStatus.Success)
                        _foundCharacters.postValue(result.data)
                        currentCharacter.postValue(result.data.first())
                    }
                }
            } catch (e: TimeoutException) {
                Timber.e("Operation timeout in searchForCharacter: $e")
                _searchStatus.postValue(LiveDataStatus.Error)
            } catch (e: Throwable) {
                Timber.e("Error thrown in searchForCharacter: $e")
                _searchStatus.postValue(LiveDataStatus.Error)
            }
        }
    }

    fun getCharacter() {
        if (currentCharacter.value == null) {
            _getCharacterStatus.value = GetCharacterStatus.Error
            return
        }

        _getCharacterStatus.value = GetCharacterStatus.Loading

        ioScope.launch {
            try {
                val result = xivApi.getCharacter(currentCharacter.value!!.id)

                when (result) {
                    is Resource.Error -> {
                        Timber.w("Error getting character - ${result.msg}")
                        _getCharacterStatus.postValue(GetCharacterStatus.Error)
                    }
                    is Resource.Success -> result
                        .data
                        .second
                        ?.let { _getCharacterStatus.postValue(GetCharacterStatus.GotCharacter) }
                        ?: _getCharacterStatus.postValue(GetCharacterStatus.GotInfo)
                }
            } catch (e: TimeoutException) {
                Timber.e("Operation timeout in getCharacter: $e")
                _getCharacterStatus.postValue(GetCharacterStatus.Error)
            } catch (e: Throwable) {
                Timber.e("Error thrown in getCharacter: $e")
                _getCharacterStatus.postValue(GetCharacterStatus.Error)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
