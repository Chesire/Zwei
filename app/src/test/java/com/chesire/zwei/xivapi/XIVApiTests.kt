package com.chesire.zwei.xivapi

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

class XIVApiTests {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    @Ignore("Ignored as needs some more work, as sometimes it just throws error and never recieved loading?")
    fun `starting XIVApi#searchForCharacter changes data to loading`() = runBlocking {
        val mockService = mock<XIVApiService> { }
        val observer = mock<(Observer<Resource<List<SearchCharacterModel>>>)> { }
        var response: LiveData<Resource<List<SearchCharacterModel>>>? = null

        val classUnderTest = XIVApi(mockService)

        launch {
            response = classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").apply {
                observeForever(observer)
            }
        }.join()

        response?.removeObserver { }
        verify(observer).onChanged(Resource.loading(emptyList()))
    }

    @Test
    fun `when XIVApi#searchForCharacter throws exception change data to error`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } doThrow NullPointerException()
        }
        val observer = mock<(Observer<Resource<List<SearchCharacterModel>>>)> { }
        var response: LiveData<Resource<List<SearchCharacterModel>>>? = null

        val classUnderTest = XIVApi(mockService)

        launch {
            response = classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").apply {
                observeForever(observer)
            }
        }.join()

        response?.removeObserver { }
        verify(observer).onChanged(
            Resource.error(
                "Exception occurred trying to find character Cheshire Cat, on Phoenix: java.lang.NullPointerException",
                emptyList()
            )
        )
    }

    @Test
    fun `when XIVApi#searchForCharacter fails change data to error`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                searchForCharacter(
                    "Cheshire Cat",
                    "Phoenix"
                )
            } doReturn async {
                Response.error<SearchCharacterResponse>(404, mock {})
            }
        }
        val observer = mock<(Observer<Resource<List<SearchCharacterModel>>>)> { }
        var response: LiveData<Resource<List<SearchCharacterModel>>>? = null

        val classUnderTest = XIVApi(mockService)

        launch {
            response = classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").apply {
                observeForever(observer)
            }
        }.join()

        response?.removeObserver { }
        verify(observer).onChanged(
            Resource.error(
                "Failure to find character Cheshire Cat, on Phoenix",
                emptyList()
            )
        )
    }

    @Test
    fun `when XIVApi#searchForCharacter is successful change data to success`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                searchForCharacter(
                    "Cheshire Cat",
                    "Phoenix"
                )
            } doReturn async {
                Response.success<SearchCharacterResponse>(mock {})
            }
        }
        val observer = mock<(Observer<Resource<List<SearchCharacterModel>>>)> { }
        var response: LiveData<Resource<List<SearchCharacterModel>>>? = null

        val classUnderTest = XIVApi(mockService)

        launch {
            response = classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").apply {
                observeForever(observer)
            }
        }.join()

        response?.removeObserver { }
        verify(observer).onChanged(Resource.success(emptyList()))
    }
}