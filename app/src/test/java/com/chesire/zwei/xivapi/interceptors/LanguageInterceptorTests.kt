package com.chesire.zwei.xivapi.interceptors

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LanguageInterceptorTests {
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `ensure adds 'cn' to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("cn")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("language=cn"))
    }

    @Test
    fun `ensure adds 'de' to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("de")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("language=de"))
    }

    @Test
    fun `ensure adds 'en' to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("en")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("language=en"))
    }

    @Test
    fun `ensure adds 'fr' to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("fr")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("language=fr"))
    }

    @Test
    fun `ensure adds 'ja' to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("ja")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("language=ja"))
    }

    @Test
    fun `ensure adds 'kr' to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("kr")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("language=kr"))
    }

    private fun getOkHttpClient(lang: String): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(LanguageInterceptor(lang))
            .build()
    }

    private fun sendMockRequest(okHttp: OkHttpClient) {
        okHttp.newCall(
            Request.Builder()
                .url(mockWebServer.url("/"))
                .build()
        ).execute()
    }
}