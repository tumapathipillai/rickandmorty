package com.ippon.rickandmorty.views.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ippon.rickandmorty.extension.OnBottomReached
import com.ippon.rickandmorty.extension.darkGray
import com.ippon.rickandmorty.helpers.paging.PageListState
import com.ippon.rickandmorty.helpers.paging.PageState

@Composable
fun <T> PageList(
    state: PageListState<T>,
    itemCard: @Composable (T) -> Unit,
    listHeader: @Composable () -> Unit,
    firstPageLoading: @Composable () -> Unit,
    firstPageError: @Composable (String) -> Unit,
    morePageLoading: @Composable () -> Unit,
    morePageError: @Composable (String) -> Unit,
    noMorePage: @Composable () -> Unit,
    loadItems: () -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()

    if (state.firstPage == state.nextPage && state.pageState is PageState.Error) {
        firstPageError((state.pageState as PageState.Error).message)
    } else if (state.firstPage == state.nextPage && state.pageState.isLoading) {
        firstPageLoading()
    } else {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            listHeader()
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .background(Color.darkGray)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.items) {
                    itemCard(it)
                }
                item {
                    if (state.nextPage == null) {
                        noMorePage()
                    } else if (state.pageState.isLoading) {
                        morePageLoading()
                    } else if (state.pageState is PageState.Error) {
                        morePageError((state.pageState as PageState.Error).message)
                    }
                }
            }
        }
    }

    listState.OnBottomReached(margin = 7) {
        loadItems()
    }

    LaunchedEffect(
        key1 = state,
    ) {
        if (state.firstPage == state.nextPage && state.pageState.isLoading) {
            listState.scrollToItem(0)
        }
    }
}