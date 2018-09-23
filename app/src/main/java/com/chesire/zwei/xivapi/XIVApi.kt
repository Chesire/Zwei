package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.model.SearchCharacterModel
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class XIVApi @Inject constructor(
    private val xivApiService: XIVApiService
) {
    fun searchForCharacter(
        name: String,
        server: String
    ): Deferred<Resource<List<SearchCharacterModel>>> = async {
        val res = xivApiService.searchForCharacter(name, server).await()
        if (res.isSuccessful) {
            return@async Resource.success(res.body()!!.characters)
        } else {
            return@async Resource.error<List<SearchCharacterModel>>(
                "Failure to find character $name, on $server",
                emptyList()
            )
        }
    }
}