package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.model.AchievementsModel
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.CharacterModel
import com.chesire.zwei.xivapi.model.InfoModel
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
        if (res.isSuccessful && res.body() != null) {
            return@async Resource.success(res.body()!!.characters)
        } else {
            return@async Resource.error<List<SearchCharacterModel>>(
                "Failure to find character $name, on $server",
                emptyList()
            )
        }
    }

    /**
     * Gets the details for the character with id of [id], if it is not yet added to the database
     * then a [InfoModel] will be returned, if it is added then a [CharacterDetailModel] will be returned.
     */
    fun getCharacter(id: Int): Deferred<Resource<Any>> = async {
        val res = xivApiService.getCharacter(id).await()
        if (res.isSuccessful && res.body() != null) {
            val body = res.body()!!
            if (body.character == null || body.achievements == null) {
                return@async Resource.success(body.info)
            } else {
                return@async Resource.success(
                    makeCharacterDetails(
                        body.character,
                        body.achievements
                    )
                )
            }
        } else {
            return@async Resource.error("Failure to get character with id $id", Any())
        }
    }

    private fun makeCharacterDetails(
        character: CharacterModel,
        achievements: AchievementsModel
    ): CharacterDetailModel {
        return CharacterDetailModel(
            achievements.list.map { it.getValue("ID") },
            character.avatar,
            character.bio,
            character.gender,
            character.id,
            character.minions,
            character.mounts,
            character.name,
            character.portrait,
            character.race,
            character.server,
            character.title
        )
    }
}
