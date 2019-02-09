package com.chesire.zwei.view.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chesire.zwei.util.IOContext
import com.chesire.zwei.view.SingleLiveEvent
import com.chesire.zwei.view.onboarding.character.GetCharacterStatus
import com.chesire.zwei.xivapi.Status
import com.chesire.zwei.xivapi.XIVApi
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.InfoModel
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private val uiScope = CoroutineScope(job + Dispatchers.Main)
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

    fun searchForCharacter() {
        if (characterName.value.isNullOrBlank() || world.value.isNullOrBlank()) {
            _searchStatus.value = Status.Error
            return
        }

        _searchStatus.value = Status.Loading
        uiScope.launch(ioContext) {
            try {
                val result = xivApi.searchForCharacter(characterName.value!!, world.value!!)

                when (result.status) {
                    Status.Error -> _searchStatus.postValue(Status.Error)
                    Status.Success -> {
                        if (result.data.isEmpty()) {
                            Timber.d("Could not find character - failure no data")
                            _searchStatus.postValue(Status.Error)
                        } else {
                            Timber.d("Found ${result.data.count()} characters")
                            _searchStatus.postValue(Status.Success)
                            _foundCharacters.postValue(result.data)
                            currentCharacter.postValue(result.data.first())
                        }
                    }
                    else -> {
                        Timber.w("Got unexpected branch ${result.status}")
                        _searchStatus.postValue(Status.Error)
                    }
                }
            } catch (e: TimeoutException) {
                Timber.e("Operation timeout in searchForCharacter: $e")
                _searchStatus.postValue(Status.Error)
            } catch (e: Throwable) {
                Timber.e("Error thrown in searchForCharacter: $e")
                _searchStatus.postValue(Status.Error)
            }
        }
    }

    fun getCharacter() {
        if (currentCharacter.value == null) {
            _getCharacterStatus.value = GetCharacterStatus.Error
            return
        }

        _getCharacterStatus.value = GetCharacterStatus.Loading

        uiScope.launch(ioContext) {
            try {
                val result = xivApi.getCharacter(currentCharacter.value!!.id)

                when (result.status) {
                    Status.Error -> _getCharacterStatus.postValue(GetCharacterStatus.Error)
                    Status.Success -> {
                        when (result.data) {
                            is InfoModel -> {
                                _getCharacterStatus.postValue(GetCharacterStatus.GotInfo)
                            }
                            is CharacterDetailModel -> {
                                _getCharacterStatus.postValue(GetCharacterStatus.GotCharacter)
                            }
                        }
                    }
                    else -> {
                        Timber.w("Got unexpected branch ${result.status} with data type ${result.data.javaClass}")
                        _getCharacterStatus.postValue(GetCharacterStatus.Error)
                    }
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
