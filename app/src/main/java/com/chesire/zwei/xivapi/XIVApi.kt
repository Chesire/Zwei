package com.chesire.zwei.xivapi

import android.arch.lifecycle.MutableLiveData
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext

class XIVApi(
    private val xivApiService: XIVApiService
) {
    fun searchForCharacter(
        name: String,
        server: String
    ): MutableLiveData<Resource<List<SearchCharacterModel>>> {
        val result = MutableLiveData<Resource<List<SearchCharacterModel>>>()
        result.value = Resource.loading(emptyList())

        launch {
            try {
                val response = xivApiService.searchForCharacter(name, server).await()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        result.value = Resource.success(response.body()!!.characters)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        result.value = Resource.error(
                            "Failure to find character $name, on $server",
                            emptyList()
                        )
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    result.value = Resource.error(
                        "Failure to find character $name, on $server",
                        emptyList()
                    )
                }
            }
        }

        return result
    }
}