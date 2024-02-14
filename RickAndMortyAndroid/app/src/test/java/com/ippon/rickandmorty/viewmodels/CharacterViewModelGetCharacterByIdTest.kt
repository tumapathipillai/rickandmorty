package com.ippon.rickandmorty.viewmodels

import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Episode
import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.Status
import com.ippon.rickandmorty.service.FakeCharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelGetCharacterByIdTest {
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCharacter_returnsData() = runTest {
        val service = FakeCharacterService(
            detailCharacter = DetailCharacter(
                id = 1,
                name = "CharacterName",
                image = "CharacterImage",
                status = Status.DEAD,
                gender = Gender.GENDERLESS,
                episodes = listOf(
                    Episode(name = "Episode", episode = "S01E01")
                )
            )
        )
        val viewModel = CharacterViewModel(service)

        assertEquals(
            viewModel.getCharacterDetail(1), DetailCharacter(
                id = 1,
                name = "CharacterName",
                image = "CharacterImage",
                status = Status.DEAD,
                gender = Gender.GENDERLESS,
                episodes = listOf(
                    Episode(name = "Episode", episode = "S01E01")
                )
            )
        )
    }

    @Test
    fun getCharacter_throwError() = runTest {
        val service = FakeCharacterService(
            error = "An unknown error occurred"
        )
        val viewModel = CharacterViewModel(service)

        val error = assertFailsWith<Error>("Should have throw an error") {
            viewModel.getCharacterDetail(1)
        }
        assertEquals(error.message, "An unknown error occurred")
    }
}