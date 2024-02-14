package com.ippon.rickandmorty.extensions

import com.ippon.rickandmorty.api.ListResponse
import com.ippon.rickandmorty.apollo.ListCharacterQuery
import com.ippon.rickandmorty.extension.toListResponse
import com.ippon.rickandmorty.models.SimpleCharacter
import org.junit.Test
import kotlin.test.assertEquals

class ListCharacterQueryExtensionTest {
    @Test
    fun listResponseMapper_emptyList() {
        val expect = ListResponse(emptyList())
        val actual = ListCharacterQuery.Characters(
            info = null,
            results = emptyList()
        ).toListResponse()

        assertEquals(expect, actual)
    }

    @Test
    fun listResponseMapper_nullList() {
        val expect = ListResponse(emptyList())
        val actual = ListCharacterQuery.Characters(
            info = null,
            results = null
        ).toListResponse()

        assertEquals(expect, actual)
    }

    @Test
    fun listResponseMapper_listWithNull() {
        val expect = ListResponse(emptyList())
        val actual = ListCharacterQuery.Characters(
            info = null,
            results = listOf(null, null)
        ).toListResponse()

        assertEquals(expect, actual)
    }

    @Test
    fun listResponseMapper_dataWithNullValueList() {
        val expect = ListResponse(emptyList())
        val actual = ListCharacterQuery.Characters(
            info = null,
            results = listOf(
                ListCharacterQuery.Result(id = null, name = null, image = null)
            )
        ).toListResponse()

        assertEquals(expect, actual)
    }

    @Test
    fun listResponseMapper_dataList() {
        val expect =
            ListResponse(
                characters = listOf(
                    SimpleCharacter(
                        id = 2,
                        name = "CharacterName",
                        image = "CharacterImage"
                    )
                ),
                nextPage = 1
            )

        val actual = ListCharacterQuery.Characters(
            info = ListCharacterQuery.Info(next = 1),
            results = listOf(
                null,
                ListCharacterQuery.Result(id = null, name = "null", image = "null"),
                ListCharacterQuery.Result(id = "null", name = "null", image = null),
                ListCharacterQuery.Result(id = "1", name = null, image = "null"),
                ListCharacterQuery.Result(
                    id = "2",
                    name = "CharacterName",
                    image = "CharacterImage"
                )
            )
        ).toListResponse()

        assertEquals(expect, actual)
    }
}