package com.ippon.rickandmorty.extension

import com.ippon.rickandmorty.apollo.DetailCharacterQuery
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Episode

fun List<DetailCharacterQuery.Episode?>.toEpisode(): List<Episode> {
    return this.filter {
        it?.name != null && it.episode != null
    }.map {
        Episode(
            name = it?.name!!,
            episode = it.episode!!,
        )
    }
}

fun DetailCharacterQuery.Character.toDetailResponse(): DetailCharacter? {
    if (
        (this.id != null) &&
        (this.name != null) &&
        (this.image != null) &&
        (this.status != null) &&
        (this.gender != null) &&
        (this.status.toStatus() != null) &&
        (this.gender.toGender() != null)
    ) {
        return DetailCharacter(
            id = this.id.toInt(),
            name = this.name,
            image = this.image,
            status = this.status.toStatus()!!,
            gender = this.gender.toGender()!!,
            episodes = this.episode.toEpisode()
        )
    }

    return null
}