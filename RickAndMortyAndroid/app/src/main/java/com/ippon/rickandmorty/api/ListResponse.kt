package com.ippon.rickandmorty.api

import com.ippon.rickandmorty.models.SimpleCharacter

data class ListResponse(val characters: List<SimpleCharacter>, val nextPage: Int? = null)