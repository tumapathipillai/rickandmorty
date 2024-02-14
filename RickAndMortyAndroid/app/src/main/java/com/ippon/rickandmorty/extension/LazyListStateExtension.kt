package com.ippon.rickandmorty.extension

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*

@Composable
fun LazyListState.OnBottomReached(
    margin: Int = 0,
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - margin
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow {
            shouldLoadMore.value
        }.collect {
            if (it) loadMore()
        }
    }
}