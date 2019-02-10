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
            Resource.Success(res.body()!!.characters)
        } else {
            Resource.Error("Failure to find character $name, on $server")
        }
    }

    /**
     * Gets the details for the character with id of [id], if it is not yet added to the database
     * then data.second will be null, if it is added then data.second will be populated.
     */
    @Suppress("PreferToOverPairSyntax")
    suspend fun getCharacter(id: Int): Resource<Pair<InfoModel, CharacterDetailModel?>> {
        val res = xivApiService.getCharacter(id).await()

        return if (res.isSuccessful && res.body() != null) {
            val body = res.body()!!
            if (body.character == null || body.achievements == null) {
                Resource.Success(Pair<InfoModel, CharacterDetailModel?>(body.info, null))
            } else {
                Resource.Success(
                    Pair<InfoModel, CharacterDetailModel?>(
                        body.info,
                        makeCharacterDetails(body.character, body.achievements)
                    )
                )
            }
        } else {
            Resource.Error("Failure to get character with id $id")
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
