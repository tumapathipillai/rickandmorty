package com.ippon.rickandmorty.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ippon.rickandmorty.api.ApiResponse
import com.ippon.rickandmorty.helpers.paging.PageState
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Filter
import com.ippon.rickandmorty.service.CharacterService
import com.ippon.rickandmorty.state.ListCharacterListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterService: CharacterService) : ViewModel() {
    var state by mutableStateOf(ListCharacterListState(firstPage = 0))
        private set

    private val _state = MutableStateFlow(ListCharacterListState(firstPage = 0))
    val stateE = _state.asStateFlow()

    fun resetCharacters(filter: Filter) {
        state = ListCharacterListState(firstPage = 0, selectedFilter = filter)
        loadCharacters()
    }

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        if (!state.pageState.isLoading && state.nextPage != null)
            viewModelScope.launch {
                state = state.copy(pageState = PageState.Loading(true))
                state = when (val response =
                    characterService.listCharacters(state.nextPage!!, state.selectedFilter)
                ) {
                    is ApiResponse.Data -> state.copy(
                        characters = state.characters + (response.data.characters),
                        pageState = PageState.Loading(false),
                        nextPage = response.data.nextPage
                    )
                    is ApiResponse.Error -> state.copy(pageState = PageState.Error(response.error))
                }
            }
    }

    suspend fun getCharacterDetail(id: Int): DetailCharacter {
        when (val response = characterService.getCharacterById(id)) {
            is ApiResponse.Data -> return response.data
            is ApiResponse.Error -> throw Error(response.error)
        }
    }
}