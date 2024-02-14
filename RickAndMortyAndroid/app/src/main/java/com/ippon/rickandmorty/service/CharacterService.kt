package com.ippon.rickandmorty.service

import com.ippon.rickandmorty.api.ApiResponse
import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Filter

interface CharacterService {
    suspend fun getCharacterById(id: Int): ApiResponse<DetailCharacter>
    suspend fun listCharacters(page: Int, filter: Filter): ApiResponse<ListResponse>
}