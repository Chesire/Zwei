package com.chesire.zwei.xivapi.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to insert the language code `language=[lang]` into the query.
 */
class LanguageInterceptor(private val lang: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url()
            .newBuilder()
            .addQueryParameter("language", lang)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}