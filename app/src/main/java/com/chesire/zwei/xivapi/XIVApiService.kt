package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Provides a Retrofit service for xivapi.com
 */
interface XIVApiService {
    @GET("/character/search")
    fun searchForCharacter(
        @Query("name") name: String,
        @Query("server") world: String
    ): Call<SearchCharacterResponse>
}