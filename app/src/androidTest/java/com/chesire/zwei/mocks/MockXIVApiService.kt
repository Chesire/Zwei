package com.chesire.zwei.mocks

import com.chesire.zwei.xivapi.XIVApiService
import com.chesire.zwei.xivapi.response.GetCharacterResponse
import com.chesire.zwei.xivapi.response.GetCompanionsResponse
import com.chesire.zwei.xivapi.response.GetMountsResponse
import com.chesire.zwei.xivapi.response.GetTitlesResponse
import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class MockXIVApiService : XIVApiService {
    override fun searchForCharacter(
        name: String,
        world: String
    ): Deferred<Response<SearchCharacterResponse>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getCharacter(id: Int): Call<GetCharacterResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun requestCharacterUpdate(id: Int): Call<Int> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getCompanion(id: Int): Call<GetCompanionsResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getCompanions(): Call<GetCompanionsResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getMount(id: Int): Call<GetMountsResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getMounts(): Call<GetMountsResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitle(id: Int): Call<GetTitlesResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getTitles(): Call<GetTitlesResponse> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
