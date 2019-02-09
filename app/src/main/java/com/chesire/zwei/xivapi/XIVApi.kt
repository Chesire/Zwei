package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.model.AchievementsModel
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.CharacterModel
import com.chesire.zwei.xivapi.model.InfoModel
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import javax.inject.Inject

class XIVApi @Inject constructor(
    private val xivApiService: XIVApiService
) {
    suspend fun searchForCharacter(
        name: String,
        server: String
    ): Resource<List<SearchCharacterModel>> {
        val res = xivApiService.searchForCharacter(name, server).await()

        return if (res.isSuccessful && res.body() != null) {
            Resource.success(res.body()!!.characters)
        } else {
            Resource.error("Failure to find character $name, on $server", emptyList())
        }
    }

    /**
     * Gets the details for the character with id of [id], if it is not yet added to the database
     * then a [InfoModel] will be returned, if it is added then a [CharacterDetailModel] will be returned.
     */
    suspend fun getCharacter(id: Int): Resource<Any> {
        val res = xivApiService.getCharacter(id).await()

        return if (res.isSuccessful && res.body() != null) {
            val body = res.body()!!
            if (body.character == null || body.achievements == null) {
                Resource.success(body.info)
            } else {
                Resource.success(makeCharacterDetails(body.character, body.achievements))
            }
        } else {
            Resource.error("Failure to get character with id $id", Any())
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
