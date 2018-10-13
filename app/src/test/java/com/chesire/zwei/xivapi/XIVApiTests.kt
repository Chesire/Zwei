package com.chesire.zwei.xivapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chesire.zwei.xivapi.flags.Gender
import com.chesire.zwei.xivapi.flags.Race
import com.chesire.zwei.xivapi.model.AchievementsModel
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.CharacterModel
import com.chesire.zwei.xivapi.model.InfoModel
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import com.chesire.zwei.xivapi.response.GetCharacterResponse
import com.chesire.zwei.xivapi.response.SearchCharacterResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import retrofit2.Response

class XIVApiTests {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `when searchForCharacter fails return status error`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                searchForCharacter("Cheshire Cat", "Phoenix")
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
    fun `when searchForCharacter has empty body return status error`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } doReturn async {
                Response.success<SearchCharacterResponse>(null)
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
                searchForCharacter("Cheshire Cat", "Phoenix")
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
                searchForCharacter("Cheshire Cat", "Phoenix")
            } doReturn async {
                Response.success(SearchCharacterResponse(mock {}, characterModels))
            }
        }

        val classUnderTest = XIVApi(mockService)

        Assert.assertEquals(
            characterModels,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().data
        )
    }

    @Test
    fun `when getCharacter fails return status error`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                getCharacter(anyInt())
            } doReturn async {
                Response.error<GetCharacterResponse>(404, mock {})
            }
        }

        val classUnderTest = XIVApi(mockService)

        Assert.assertEquals(Status.Error, classUnderTest.getCharacter(0).await().status)
    }

    @Test
    fun `when getCharacter has empty body return status error`() = runBlocking {
        val mockService = mock<XIVApiService> {
            on {
                getCharacter(anyInt())
            } doReturn async {
                Response.success<GetCharacterResponse>(null)
            }
        }

        val classUnderTest = XIVApi(mockService)

        Assert.assertEquals(Status.Error, classUnderTest.getCharacter(0).await().status)
    }

    @Test
    fun `when getCharacter is successful, with null character, return status success with Info`() =
        runBlocking {
            val mockService = mock<XIVApiService> {
                on {
                    getCharacter(anyInt())
                } doReturn async {
                    Response.success<GetCharacterResponse>(mock {
                        on { info }.thenReturn(mock { })
                        on { character }.thenReturn(null)
                        on { achievements }.thenReturn(getAchievementsModel())
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            Assert.assertEquals(Status.Success, result.status)
            Assert.assertTrue(result.data is InfoModel)
        }

    @Test
    fun `when getCharacter is successful, with null achievements, return status success with Info`() =
        runBlocking {
            val mockService = mock<XIVApiService> {
                on {
                    getCharacter(anyInt())
                } doReturn async {
                    Response.success<GetCharacterResponse>(mock {
                        on { info }.thenReturn(mock { })
                        on { character }.thenReturn(getCharacterModel())
                        on { achievements }.thenReturn(null)
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            Assert.assertEquals(Status.Success, result.status)
            Assert.assertTrue(result.data is InfoModel)
        }

    @Test
    fun `when getCharacter is successful, with null character and achievements, return status success with Info`() =
        runBlocking {
            val mockService = mock<XIVApiService> {
                on {
                    getCharacter(anyInt())
                } doReturn async {
                    Response.success<GetCharacterResponse>(mock {
                        on { info }.thenReturn(mock { })
                        on { character }.thenReturn(null)
                        on { achievements }.thenReturn(null)
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            Assert.assertEquals(Status.Success, result.status)
            Assert.assertTrue(result.data is InfoModel)
        }

    @Test
    fun `when getCharacter is successful, with detailed character and achievements, return status success with characterDetailModel`() =
        runBlocking {
            val mockService = mock<XIVApiService> {
                on {
                    getCharacter(anyInt())
                } doReturn async {
                    Response.success<GetCharacterResponse>(mock {
                        on { info }.thenReturn(mock { })
                        on { character }.thenReturn(getCharacterModel())
                        on { achievements }.thenReturn(getAchievementsModel())
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            Assert.assertEquals(Status.Success, result.status)
            Assert.assertTrue(result.data is CharacterDetailModel)
        }

    private fun getCharacterModel(): CharacterModel {
        return CharacterModel(
            "avatar",
            "bio",
            Gender.Unknown,
            0,
            emptyList(),
            emptyList(),
            "name",
            "portrait",
            Race.Unknown,
            "server",
            0
        )
    }

    private fun getAchievementsModel(): AchievementsModel {
        return AchievementsModel(emptyList(), "", 0)
    }
}
