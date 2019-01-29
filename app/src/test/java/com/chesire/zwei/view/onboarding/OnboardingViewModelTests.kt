package com.chesire.zwei.view.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chesire.zwei.view.onboarding.character.GetCharacterStatus
import com.chesire.zwei.xivapi.Resource
import com.chesire.zwei.xivapi.Status
import com.chesire.zwei.xivapi.XIVApi
import com.chesire.zwei.xivapi.flags.Gender
import com.chesire.zwei.xivapi.flags.Race
import com.chesire.zwei.xivapi.flags.State
import com.chesire.zwei.xivapi.model.CharacterDetailModel
import com.chesire.zwei.xivapi.model.InfoModel
import com.chesire.zwei.xivapi.model.SearchCharacterModel
import com.chesire.zwei.xivapi.model.StateModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class OnboardingViewModelTests {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `when searchForCharacter, with no name, set search status to error`() = runBlocking {
        val mockApi = mockk<XIVApi> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Resource.success(listOf(getSearchCharacterModel()))
            }
        }

        val classUnderTest = OnboardingViewModel(mockApi).apply {
            world.value = "Phoenix"
        }

        runBlocking { classUnderTest.searchForCharacter() }.join()

        assertEquals(Status.Error, classUnderTest.searchStatus.value)
    }

    @Test
    fun `when searchForCharacter, with no world, set search status to error`() = runBlocking {
        val mockApi = mockk<XIVApi> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Resource.success(listOf(getSearchCharacterModel()))
            }
        }

        val classUnderTest = OnboardingViewModel(mockApi).apply {
            characterName.value = "Cheshire Cat"
        }

        runBlocking { classUnderTest.searchForCharacter() }.join()

        assertEquals(Status.Error, classUnderTest.searchStatus.value)
    }

    @Test
    fun `when searchForCharacter fails, set search status to error`() = runBlocking {
        val mockApi = mockk<XIVApi> {
            every {
                searchForCharacter("Cheshire Cat", "Phoenix")
            } returns async {
                Resource.error<List<SearchCharacterModel>>("Test", mockk())
            }
        }

        val classUnderTest = OnboardingViewModel(mockApi).apply {
            characterName.value = "Cheshire Cat"
            world.value = "Phoenix"
        }

        runBlocking { classUnderTest.searchForCharacter() }.join()

        assertEquals(Status.Error, classUnderTest.searchStatus.value)
    }

    @Test
    fun `when searchForCharacter succeeds, with no characters, set search status to error`() =
        runBlocking {
            val mockApi = mockk<XIVApi> {
                every {
                    searchForCharacter("Cheshire Cat", "Phoenix")
                } returns async {
                    Resource.success<List<SearchCharacterModel>>(emptyList())
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
            }

            runBlocking { classUnderTest.searchForCharacter() }.join()

            assertEquals(Status.Error, classUnderTest.searchStatus.value)
        }

    @Test
    fun `when searchForCharacter succeeds, with characters, set search status to success`() =
        runBlocking {
            val mockApi = mockk<XIVApi> {
                every {
                    searchForCharacter("Cheshire Cat", "Phoenix")
                } returns async {
                    Resource.success(listOf(getSearchCharacterModel()))
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
            }

            runBlocking { classUnderTest.searchForCharacter() }.join()

            assertEquals(Status.Success, classUnderTest.searchStatus.value)
        }

    @Test
    fun `when searchForCharacter succeeds, with characters, populate the foundCharacters`() =
        runBlocking {
            val expectedCharacters = listOf(getSearchCharacterModel())
            val mockApi = mockk<XIVApi> {
                every {
                    searchForCharacter("Cheshire Cat", "Phoenix")
                } returns async {
                    Resource.success(expectedCharacters)
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
            }

            runBlocking { classUnderTest.searchForCharacter() }.join()

            assertEquals(expectedCharacters, classUnderTest.foundCharacters.value)
        }

    @Test
    fun `when searchForCharacter succeeds, with characters, set the current character to the first found`() =
        runBlocking {
            val expectedCharacters = listOf(getSearchCharacterModel(0), getSearchCharacterModel(1))
            val mockApi = mockk<XIVApi> {
                every {
                    searchForCharacter("Cheshire Cat", "Phoenix")
                } returns async {
                    Resource.success(expectedCharacters)
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
            }

            runBlocking { classUnderTest.searchForCharacter() }.join()

            assertEquals(expectedCharacters.first(), classUnderTest.currentCharacter.value)
        }

    @Test
    fun `when getCharacter, with no current character, set get character status to error`() =
        runBlocking {
            val mockApi = mockk<XIVApi> {
                every {
                    getCharacter(0)
                } returns async {
                    Resource.success(getCharacterDetailModel())
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
            }

            runBlocking { classUnderTest.getCharacter() }.join()

            assertEquals(GetCharacterStatus.Error, classUnderTest.getCharacterStatus.value)
        }

    @Test
    fun `when getCharacter fails, set get character status to error`() = runBlocking {
        val mockApi = mockk<XIVApi> {
            every {
                getCharacter(0)
            } returns async {
                Resource.error<Resource<Any>>("test", mockk())
            }
        }

        val classUnderTest = OnboardingViewModel(mockApi).apply {
            characterName.value = "Cheshire Cat"
            world.value = "Phoenix"
            currentCharacter.value = getSearchCharacterModel()
        }

        runBlocking { classUnderTest.getCharacter() }.join()

        assertEquals(GetCharacterStatus.Error, classUnderTest.getCharacterStatus.value)
    }

    @Test
    fun `when getCharacter reports unexpected branch, set get character status to error`() =
        runBlocking {
            val mockApi = mockk<XIVApi> {
                every {
                    getCharacter(0)
                } returns async {
                    Resource.loading<Resource<Any>>(mockk())
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
                currentCharacter.value = getSearchCharacterModel()
            }

            runBlocking { classUnderTest.getCharacter() }.join()

            assertEquals(GetCharacterStatus.Error, classUnderTest.getCharacterStatus.value)
        }

    @Test
    fun `when getCharacter succeeds, with InfoModel, set get character status to GotInfo`() =
        runBlocking {
            val mockApi = mockk<XIVApi> {
                every {
                    getCharacter(0)
                } returns async {
                    Resource.success(getInfoModel())
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
                currentCharacter.value = getSearchCharacterModel()
            }

            runBlocking { classUnderTest.getCharacter() }.join()

            assertEquals(GetCharacterStatus.GotInfo, classUnderTest.getCharacterStatus.value)
        }

    @Test
    fun `when getCharacter succeeds, with CharacterDetailModel, set get character status to GotCharacter`() =
        runBlocking {
            val mockApi = mockk<XIVApi> {
                every {
                    getCharacter(0)
                } returns async {
                    Resource.success(getCharacterDetailModel())
                }
            }

            val classUnderTest = OnboardingViewModel(mockApi).apply {
                characterName.value = "Cheshire Cat"
                world.value = "Phoenix"
                currentCharacter.value = getSearchCharacterModel()
            }

            runBlocking { classUnderTest.getCharacter() }.join()

            assertEquals(GetCharacterStatus.GotCharacter, classUnderTest.getCharacterStatus.value)
        }

    private fun getSearchCharacterModel(id: Int = 0): SearchCharacterModel {
        return SearchCharacterModel(id, "name", "server", "avatar")
    }

    private fun getInfoModel(): InfoModel {
        return InfoModel(
            StateModel(State.Cached, ""),
            StateModel(State.Cached, ""),
            StateModel(State.Cached, ""),
            StateModel(State.Cached, ""),
            StateModel(State.Cached, "")
        )
    }

    private fun getCharacterDetailModel(): CharacterDetailModel {
        return CharacterDetailModel(
            emptyList(),
            "",
            "",
            Gender.Unknown,
            0,
            emptyList(),
            emptyList(),
            "",
            "",
            Race.Unknown,
            "",
            0
        )
    }
}
