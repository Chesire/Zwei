package com.chesire.zwei.xivapi.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to insert [key]=[value] into the query.
 */
class InterceptorOverride(private val key: String, private val value: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url()
            .newBuilder()
            .addQueryParameter(key, value)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
