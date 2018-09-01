package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.response.GetCharacterResponse
import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Provides a Retrofit service for xivapi.com
 */
interface XIVApiService {
    /**
     * Searches for character on the lodestone.
     * XIVApi: http://xivapi.com/docs/Character#section-1
     */
    @GET("/character/search")
    fun searchForCharacter(
        @Query("name") name: String,
        @Query("server") world: String
    ): Call<SearchCharacterResponse>

    /**
     * Gets character details from XIVApi.
     * XIVApi: http://xivapi.com/docs/Character#section-2
     */
    @GET("/character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<GetCharacterResponse>
}