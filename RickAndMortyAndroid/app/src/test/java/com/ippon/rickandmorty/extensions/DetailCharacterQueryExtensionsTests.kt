package com.ippon.rickandmorty.extensions

import com.ippon.rickandmorty.apollo.DetailCharacterQuery
import com.ippon.rickandmorty.extension.toDetailResponse
import com.ippon.rickandmorty.extension.toEpisode
import com.ippon.rickandmorty.models.DetailCharacter
import com.ippon.rickandmorty.models.Episode
import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.Status
import org.junit.Test
import kotlin.test.assertEquals

class DetailCharacterQueryExtensionsTests {
    @Test
    fun listEpisodeMapper_EmptyList() {
        val expect = listOf<Episode>()
        val actual = listOf<DetailCharacterQuery.Episode>().toEpisode()

        assertEquals(expect, actual)
    }

    @Test
    fun listEpisodeMapper_ListWithNullValue() {
        val expect = listOf<Episode>()

        val actual = listOf(
            DetailCharacterQuery.Episode(name = null, episode = "Should not be mapped"),
            DetailCharacterQuery.Episode(name = "Should not be loaded", episode = null),
            DetailCharacterQuery.Episode(name = null, episode = null),
        ).toEpisode()

        assertEquals(expect, actual)
    }

    @Test
    fun listEpisodeMapper_ListWithNonNullValue() {
        val expect = listOf(
            Episode(name = "Name Episode 1", episode = "Episode 1"),
            Episode(name = "Name Episode 2", episode = "Episode 2"),
        )

        val actual = listOf(
            DetailCharacterQuery.Episode(name = "Name Episode 1", episode = "Episode 1"),
            DetailCharacterQuery.Episode(name = "Name Episode 2", episode = "Episode 2"),
        ).toEpisode()

        assertEquals(expect, actual)
    }

    @Test
    fun detailCharacterMapper_DetailsWithNullValue() {
        val expect = null

        val actual = DetailCharacterQuery.Character(
            id = null,
            name = "Character1",
            image = "Character Image",
            status = "alive",
            gender = "male",
            episode = listOf(),
        ).toDetailResponse()

        assertEquals(expect, actual)
    }

    @Test
    fun detailCharacterMapper_DetailsWithNonNullValue() {
        val expect = DetailCharacter(
            id = 1,
            name = "Character1",
            image = "Character Image",
            status = Status.ALIVE,
            gender = Gender.MALE,
            episodes = listOf()
        )

        val actual = DetailCharacterQuery.Character(
            id = "1",
            name = "Character1",
            image = "Character Image",
            status = "alive",
            gender = "male",
            episode = listOf(),
        ).toDetailResponse()

        assertEquals(expect, actual)
    }
}