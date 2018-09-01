package com.chesire.zwei.xivapi

import com.chesire.zwei.xivapi.adapters.StateAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL = "https://xivapi.com/"

class XIVApiManager {
    private val interact: XIVApiService

    init {
        val httpClient = OkHttpClient()
            .newBuilder()

        //if (BuildConfig.DEBUG) {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        httpClient.addInterceptor(interceptor)
        //}

        val moshi = Moshi.Builder()
            .add(StateAdapter())
            .build()

        interact = Retrofit.Builder()
            .baseUrl(URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(XIVApiService::class.java)
    }

    fun searchForCharacter(name: String, server: String) {
        val ri = interact.searchForCharacter(name.replace(" ", "+"), server)
        val r = ri.execute()
        if (r.isSuccessful) {
            val body = r.body()
            val error = r.errorBody()
            val s = ""
        } else {
            val s = ""
        }
    }

    fun getCharacter(id: Int) {
        val ri = interact.getCharacter(id)
        val r = ri.execute()
        if (r.isSuccessful) {
            val body = r.body()
            val error = r.errorBody()
            val s = ""
        } else {
            val s = ""
        }
    }

    fun requestCharacterUpdate(id: Int) {
        val ri = interact.requestCharacterUpdate(id)
        val r = ri.execute()
        if (r.isSuccessful) {
            val body = r.body()
            val error = r.errorBody()
            val s = ""

            if (body == 1) {
                // Moved to front of queue
            } else {
                // Wait longer to refresh
            }
        } else {
            val s = ""
        }
    }
}