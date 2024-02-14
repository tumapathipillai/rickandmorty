package com.ippon.rickandmorty.service

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.ippon.rickandmorty.api.ApiResponse
import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.apollo.DetailCharacterQuery
import com.ippon.rickandmorty.apollo.ListCharacterQuery
import com.ippon.rickandmorty.extension.toDetailResponse
import com.ippon.rickandmorty.extension.toFilterCharacter
import com.ippon.rickandmorty.extension.toListResponse
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Filter
import java.lang.Exception

class GraphqlCharacterService(private val apolloClient: ApolloClient) : CharacterService {
    override suspend fun getCharacterById(id: Int): ApiResponse<DetailCharacter> {
        val response = apolloClient.query(DetailCharacterQuery("$id")).execute()

        response.errors?.let {
            return ApiResponse.Error("Error caught when querying : ${it.joinToString { error -> error.message }}")
        }

        response.data?.character?.let { character ->
            try {
                val result = character.toDetailResponse()
                if (result != null) {
                    return ApiResponse.Data(result)
                } else {
                    return ApiResponse.Error("Could not get the character with id: $id")
                }
            } catch (error: Exception) {
                error.message?.let { it1 -> Log.d("error", it1) }
                return ApiResponse.Error(
                    error.toString()
                )
            }
        }

        return ApiResponse.Error("Could not get the character with id: $id")
    }

    override suspend fun listCharacters(
        page: Int,
        filter: Filter
    ): ApiResponse<ListResponse> {
        val response =
            apolloClient.query(ListCharacterQuery(page, filter.toFilterCharacter()))
                .execute()

        response.errors?.let {
            return ApiResponse.Error("Error caught when querying : ${it.joinToString { error -> error.message }}")
        }

        response.data?.characters?.let {
            return ApiResponse.Data(it.toListResponse())
        }

        return ApiResponse.Data(ListResponse(emptyList()))
    }

}