package com.ippon.rickandmorty.state

import com.ippon.rickandmorty.helpers.paging.PageListState
import com.ippon.rickandmorty.helpers.paging.PageState
import com.ippon.rickandmorty.models.Filter
import com.ippon.rickandmorty.models.SimpleCharacter

data class ListCharacterListState(
    val characters: List<SimpleCharacter> = emptyList(),
    val selectedFilter: Filter = Filter(status = null, gender = null),
    override val nextPage: Int? = 0,
    override val firstPage: Int,
    override val pageState: PageState = PageState.Loading(false),
) : PageListState<SimpleCharacter> {
    override val items: List<SimpleCharacter>
        get() = characters
}