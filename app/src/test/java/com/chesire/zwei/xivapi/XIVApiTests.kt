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
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
        val mockService = mockk<XIVApiService> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Response.error<SearchCharacterResponse>(404, mockk())
            }
        }

        val classUnderTest = XIVApi(mockService)

        assertEquals(
            Status.Error,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().status
        )
    }

    @Test
    fun `when searchForCharacter has empty body return status error`() = runBlocking {
        val mockService = mockk<XIVApiService> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Response.success<SearchCharacterResponse>(null)
            }
        }

        val classUnderTest = XIVApi(mockService)

        assertEquals(
            Status.Error,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().status
        )
    }

    @Test
    fun `when searchForCharacter is successful return status success`() = runBlocking {
        val mockService = mockk<XIVApiService> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Response.success<SearchCharacterResponse>(mockk {
                    every { characters } returns mockk()
                })
            }
        }

        val classUnderTest = XIVApi(mockService)

        assertEquals(
            Status.Success,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().status
        )
    }

    @Test
    fun `when searchForCharacter is successful returns listOfSearchCharacterModel`() = runBlocking {
        val characterModels = listOf(SearchCharacterModel(0, "name", "server", "avatar"))
        val mockService = mockk<XIVApiService> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Response.success(SearchCharacterResponse(mockk(), characterModels))
            }
        }

        val classUnderTest = XIVApi(mockService)

        assertEquals(
            characterModels,
            classUnderTest.searchForCharacter("Cheshire Cat", "Phoenix").await().data
        )
    }

    @Test
    fun `when getCharacter fails return status error`() = runBlocking {
        val mockService = mockk<XIVApiService> {
            every {
                getCharacter(anyInt())
            } returns async {
                Response.error<GetCharacterResponse>(404, mockk())
            }
        }

        val classUnderTest = XIVApi(mockService)

        assertEquals(Status.Error, classUnderTest.getCharacter(0).await().status)
    }

    @Test
    fun `when getCharacter has empty body return status error`() = runBlocking {
        val mockService = mockk<XIVApiService> {
            every {
                getCharacter(anyInt())
            } returns async {
                Response.success<GetCharacterResponse>(null)
            }
        }

        val classUnderTest = XIVApi(mockService)

        assertEquals(Status.Error, classUnderTest.getCharacter(0).await().status)
    }

    @Test
    fun `when getCharacter is successful, with null character, return status success with Info`() =
        runBlocking {
            val mockService = mockk<XIVApiService> {
                every {
                    getCharacter(anyInt())
                } returns async {
                    Response.success<GetCharacterResponse>(mockk {
                        every { info } returns mockk()
                        every { character } returns null
                        every { achievements } returns getAchievementsModel()
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            assertEquals(Status.Success, result.status)
            assertTrue(result.data is InfoModel)
        }

    @Test
    fun `when getCharacter is successful, with null achievements, return status success with Info`() =
        runBlocking {
            val mockService = mockk<XIVApiService> {
                every {
                    getCharacter(anyInt())
                } returns async {
                    Response.success<GetCharacterResponse>(mockk {
                        every { info } returns mockk()
                        every { character } returns getCharacterModel()
                        every { achievements } returns null
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            assertEquals(Status.Success, result.status)
            assertTrue(result.data is InfoModel)
        }

    @Test
    fun `when getCharacter is successful, with null character and achievements, return status success with Info`() =
        runBlocking {
            val mockService = mockk<XIVApiService> {
                every {
                    getCharacter(anyInt())
                } returns async {
                    Response.success<GetCharacterResponse>(mockk {
                        every { info } returns mockk()
                        every { character } returns null
                        every { achievements } returns null
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            assertEquals(Status.Success, result.status)
            assertTrue(result.data is InfoModel)
        }

    @Test
    fun `when getCharacter is successful, with detailed character and achievements, return status success with characterDetailModel`() =
        runBlocking {
            val mockService = mockk<XIVApiService> {
                every {
                    getCharacter(anyInt())
                } returns async {
                    Response.success<GetCharacterResponse>(mockk {
                        every { info } returns mockk()
                        every { character } returns getCharacterModel()
                        every { achievements } returns getAchievementsModel()
                    })
                }
            }

            val classUnderTest = XIVApi(mockService)

            val result = classUnderTest.getCharacter(0).await()
            assertEquals(Status.Success, result.status)
            assertTrue(result.data is CharacterDetailModel)
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
