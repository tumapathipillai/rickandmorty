package com.ippon.rickandmorty.service

import com.ippon.rickandmorty.api.ApiResponse
import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Filter
import kotlinx.coroutines.delay

class FakeCharacterService(
    private val delay: Long = 0,
    var detailCharacter: DetailCharacter? = null,
    var listResponse: ListResponse? = null,
    var error: String? = null,
) : CharacterService {
    var getCharacterByIdInvocation = 0
    var listCharactersInvocation = 0

    override suspend fun getCharacterById(id: Int): ApiResponse<DetailCharacter> {
        getCharacterByIdInvocation++
        delay(timeMillis = delay)

        return if (error != null) {
            ApiResponse.Error(error!!)
        } else if (detailCharacter == null) {
            ApiResponse.Error("No data provided")
        } else {
            ApiResponse.Data(detailCharacter!!)
        }

    }

    override suspend fun listCharacters(page: Int, filter: Filter): ApiResponse<ListResponse> {
        listCharactersInvocation++
        delay(timeMillis = delay)
        return if (error != null) {
            ApiResponse.Error(error!!)
        } else if (listResponse == null) {
            ApiResponse.Error("No data provided")
        } else {
            ApiResponse.Data(listResponse!!)
        }
    }
}