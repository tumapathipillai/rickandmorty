package com.ippon.rickandmorty.api

sealed class ApiResponse<T> {
    data class Error<T>(val error: String) : ApiResponse<T>()
    data class Data<T>(val data: T) : ApiResponse<T>()
}