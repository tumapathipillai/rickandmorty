package com.ippon.rickandmorty.helpers.paging

interface PageListState<T> {
    val pageState: PageState
    val firstPage: Int
    val nextPage: Int?
    val items: List<T>
}