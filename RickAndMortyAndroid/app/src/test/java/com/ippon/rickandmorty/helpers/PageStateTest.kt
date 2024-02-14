package com.ippon.rickandmorty.helpers

import com.ippon.rickandmorty.helpers.paging.PageState
import org.junit.Test

class PageStateTest {
    @Test
    fun errorIsNotLoading() {
        assert(!PageState.Error("").isLoading)
    }

    @Test
    fun loadingIsNotLoading_IsFalse() {
        assert(!PageState.Loading(false).isLoading)
    }

    @Test
    fun loadingIsLoading_IsTrue() {
        assert(PageState.Loading(true).isLoading)
    }
}