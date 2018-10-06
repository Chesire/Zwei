package com.chesire.zwei.xivapi.interceptors

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class InterceptorOverrideTests {
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
    fun `ensure adds key to requests`() {
        mockWebServer.enqueue(MockResponse())

        val okHttp = getOkHttpClient("key", "0123456789abcdef")
        sendMockRequest(okHttp)
        val request = mockWebServer.takeRequest()

        Assert.assertTrue(request.path.contains("key=0123456789abcdef"))
    }

    private fun getOkHttpClient(key: String, value: String): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(InterceptorOverride(key, value))
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
