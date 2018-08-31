package com.chesire.zwei.xivapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL = "https://xivapi.com/"

class XIVApiManager {
    private val interact: XIVApiService

    init {
        val httpClient = OkHttpClient()
            .newBuilder()

        //if (BuildConfig.DEBUG) {
        //val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        //    this.level = HttpLoggingInterceptor.Level.BODY
        //}

        //httpClient.addInterceptor(interceptor)
        //}

        interact = Retrofit.Builder()
            .baseUrl(URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(XIVApiService::class.java)
    }

    public fun searchForCharacter(name: String, server: String) {
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
}