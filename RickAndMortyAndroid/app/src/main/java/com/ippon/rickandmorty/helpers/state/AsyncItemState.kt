package com.ippon.rickandmorty.helpers.state

sealed class AsyncItemState<T> {
    data class Success<T>(val data: T) : AsyncItemState<T>()
    data class Error<T>(val message: String) : AsyncItemState<T>()
    class Loading<T> : AsyncItemState<T>()
}
