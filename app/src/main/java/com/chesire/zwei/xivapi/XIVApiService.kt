package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.response.GetCharacterResponse
import com.chesire.zwei.xivapi.response.GetCompanionsResponse
import com.chesire.zwei.xivapi.response.GetMountsResponse
import com.chesire.zwei.xivapi.response.GetTitlesResponse
import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Provides a Retrofit service for xivapi.com.
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
    ): Deferred<Response<SearchCharacterResponse>>

    /**
     * Gets character details from XIVApi.
     * XIVApi: http://xivapi.com/docs/Character#section-2
     *
     * @param id character id should be passed in
     */
    @GET("/character/{id}?data=AC,FR,FC,FCM,PVP")
    fun getCharacter(@Path("id") id: Int): Call<GetCharacterResponse>

    /**
     * Requests character details are updated on XIVApi.
     * XIVApi: http://xivapi.com/docs/Character#section-4
     *
     * @param id character id should be passed in
     *
     * @return 1 if pushed to front of queue, 0 if need to wait longer to update.
     */
    @GET("/character/{id}/update")
    fun requestCharacterUpdate(@Path("id") id: Int): Call<Int>

    /**
     * Gets data for the companion with id of [id] from XIVApi.
     */
    @GET("/Companion?Columns=ID,Icon,IconID,IconSmall,Name,Url,Description,Tooltip")
    fun getCompanion(@Query("ids") id: Int): Call<GetCompanionsResponse>

    /**
     * Gets data for all companions from XIVApi.
     */
    @GET("/Companion?Columns=ID,Icon,IconID,IconSmall,Name,Url,Description,Tooltip")
    fun getCompanions(): Call<GetCompanionsResponse>

    /**
     * Gets data for all mounts from XIVApi.
     */
    @GET("/Mount?Columns=ID,Icon,IconId,IconSmall,Order,Url,Name,Description,DescriptionEnhanced,Tooltip")
    fun getMount(@Query("ids") id: Int): Call<GetMountsResponse>

    /**
     * Gets data for all mounts from XIVApi.
     */
    @GET("/Mount?Columns=ID,Icon,IconId,IconSmall,Order,Url,Name,Description,DescriptionEnhanced,Tooltip")
    fun getMounts(): Call<GetMountsResponse>

    /**
     * Gets data for the title with id of [id] from XIVApi.
     */
    @GET("/Title?Columns=ID,IsPrefix,NameFemale,Name")
    fun getTitle(@Query("ids") id: Int): Call<GetTitlesResponse>

    /**
     * Gets data for all titles from XIVApi.
     */
    @GET("/Title?Columns=ID,IsPrefix,NameFemale,Name")
    fun getTitles(): Call<GetTitlesResponse>
}