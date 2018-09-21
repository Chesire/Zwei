package com.chesire.zwei.xivapi

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

class XIVApiTests {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `when searchForCharacter fails return status error`() = runBlocking {
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

        val classUnderTest = XIVApi(mockService)

        Assert.assertEquals(
            Status.Error,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().status
        )
    }

    @Test
    fun `when searchForCharacter is successful return status success`() = runBlocking {
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

        val classUnderTest = XIVApi(mockService)

        Assert.assertEquals(
            Status.Success,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().status
        )
    }

    @Test
    fun `when searchForCharacter is successful returns listOfSearchCharacterModel`() = runBlocking {
        val characterModels = listOf(SearchCharacterModel(0, "name", "server", "avatar"))
        val mockService = mock<XIVApiService> {
            on {
                searchForCharacter(
                    "Cheshire Cat",
                    "Phoenix"
                )
            } doReturn async {
                Response.success(
                    SearchCharacterResponse(
                        mock {},
                        characterModels
                    )
                )
            }
        }

        val classUnderTest = XIVApi(mockService)

        Assert.assertEquals(
            characterModels,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().data
        )
    }
}