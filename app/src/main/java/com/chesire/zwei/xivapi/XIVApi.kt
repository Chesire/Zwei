package com.chesire.zwei.xivapi

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.experimental.launch

class XIVApi(
    private val xivApiService: XIVApiService
) {
    fun searchForCharacter(
        name: String,
        server: String
    ): LiveData<Resource<List<SearchCharacterModel>>> {
        val result = MutableLiveData<Resource<List<SearchCharacterModel>>>().apply {
            postValue(Resource.loading(emptyList()))
        }

        launch {
            try {
                val request = xivApiService.searchForCharacter(name, server)
                val response = request.await()
                if (response.isSuccessful) {
                    result.postValue(Resource.success(response.body()!!.characters))
                } else {
                    result.postValue(
                        Resource.error(
                            "Failure to find character $name, on $server",
                            emptyList()
                        )
                    )
                }
            } catch (ex: Exception) {
                result.postValue(
                    Resource.error(
                        "Exception occurred trying to find character $name, on $server: $ex",
                        emptyList()
                    )
                )
            }
        }

        return result
    }
}