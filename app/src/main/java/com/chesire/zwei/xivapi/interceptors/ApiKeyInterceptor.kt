package com.chesire.zwei.xivapi.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to insert the XIVApiKey `key=[apiKey]` into the query.
 *
 * Supported languages for XIVApi are documented at: https://xivapi.com/docs#section-4
 */
class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url()
            .newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}