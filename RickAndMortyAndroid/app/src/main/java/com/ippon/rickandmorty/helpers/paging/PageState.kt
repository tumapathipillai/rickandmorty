package com.ippon.rickandmorty.helpers.paging

sealed class PageState {
    data class Error(val message: String) : PageState()
    data class Loading(val loading: Boolean) : PageState()

    val isLoading: Boolean
        get() = this is Loading && this.loading
}