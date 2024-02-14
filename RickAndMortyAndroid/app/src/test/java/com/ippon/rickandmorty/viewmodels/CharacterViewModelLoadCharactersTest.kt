package com.ippon.rickandmorty.viewmodels

import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.helpers.paging.PageState
import com.ippon.rickandmorty.models.Filter
import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.SimpleCharacter
import com.ippon.rickandmorty.models.Status
import com.ippon.rickandmorty.service.FakeCharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelLoadCharactersTest {
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
    fun init_shouldLoadCharacters() = runTest {
        val service = FakeCharacterService(
            delay = 1000,
            listResponse = ListResponse(characters = emptyList())
        )
        val viewModel = CharacterViewModel(service)
        assertEquals(service.listCharactersInvocation, 0)

        advanceTimeBy(1)
        assert(viewModel.state.pageState.isLoading)

        advanceUntilIdle()
        assert(!viewModel.state.pageState.isLoading)
        assertEquals(service.listCharactersInvocation, 1)
        assertEquals(
            viewModel.state.characters,
            emptyList()
        )
    }

    @Test
    fun reset_shouldResetStateWithFilterAndLoadCharacters() = runTest {
        val service = FakeCharacterService(
            delay = 1000,
            listResponse = ListResponse(characters = emptyList())
        )
        val viewModel = CharacterViewModel(service)
        assertEquals(service.listCharactersInvocation, 0)

        advanceUntilIdle()
        assertEquals(service.listCharactersInvocation, 1)

        viewModel.resetCharacters(Filter(status = Status.DEAD, gender = Gender.GENDERLESS))
        advanceUntilIdle()
        assertEquals(
            viewModel.state.selectedFilter,
            Filter(status = Status.DEAD, gender = Gender.GENDERLESS)
        )
        assertEquals(service.listCharactersInvocation, 2)
    }

    @Test
    fun loadCharacters_appendCharactersToState() = runTest {
        val service = FakeCharacterService(
            delay = 1000,
            listResponse = ListResponse(
                nextPage = 1,
                characters = listOf(
                    SimpleCharacter(id = 1, name = "CharacterName", image = "CharacterImage")
                )
            )
        )
        val viewModel = CharacterViewModel(service)

        advanceUntilIdle()
        assertEquals(
            viewModel.state.characters,
            listOf(
                SimpleCharacter(id = 1, name = "CharacterName", image = "CharacterImage")
            )
        )

        service.listResponse = ListResponse(
            characters = listOf(
                SimpleCharacter(id = 2, name = "CharacterName2", image = "CharacterImage2")
            )
        )

        viewModel.loadCharacters()
        advanceUntilIdle()

        assertEquals(
            viewModel.state.characters,
            listOf(
                SimpleCharacter(id = 1, name = "CharacterName", image = "CharacterImage"),
                SimpleCharacter(id = 2, name = "CharacterName2", image = "CharacterImage2"),
            )
        )
    }

    @Test
    fun loadCharacters_appendError() = runTest {
        val service = FakeCharacterService(
            delay = 1000,
            listResponse = ListResponse(
                nextPage = 1,
                characters = listOf(
                    SimpleCharacter(id = 1, name = "CharacterName", image = "CharacterImage")
                )
            )
        )
        val viewModel = CharacterViewModel(service)


        advanceUntilIdle()
        assertEquals(
            viewModel.state.characters,
            listOf(
                SimpleCharacter(id = 1, name = "CharacterName", image = "CharacterImage")
            )
        )

        service.error = "An unknown error occurred"

        viewModel.loadCharacters()
        advanceUntilIdle()
        assertEquals(
            viewModel.state.characters,
            listOf(
                SimpleCharacter(id = 1, name = "CharacterName", image = "CharacterImage")
            )
        )
        assert(viewModel.state.pageState is PageState.Error)
        assertEquals(
            (viewModel.state.pageState as PageState.Error).message,
            "An unknown error occurred"
        )
    }
}