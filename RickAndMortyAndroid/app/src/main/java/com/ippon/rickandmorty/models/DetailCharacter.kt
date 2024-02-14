package com.ippon.rickandmorty.models

data class DetailCharacter(
    val id: Int,
    val image: String,
    val name: String,
    val status: Status,
    val gender: Gender,
    val episodes: List<Episode>
)
