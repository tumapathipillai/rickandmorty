package com.ippon.rickandmorty.extension

import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.apollo.ListCharacterQuery
import com.ippon.rickandmorty.models.SimpleCharacter

fun ListCharacterQuery.Characters.toListResponse(): ListResponse {
    this.results?.let { it ->
        val characters = it.filterNotNull().filter { character ->
            character.id != null && character.name != null && character.image != null
        }.map { character ->
            SimpleCharacter(
                character.id!!.toInt(),
                character.name!!,
                character.image!!,
            )
        }

        return ListResponse(characters, this.info?.next)
    }

    return ListResponse(emptyList())
}