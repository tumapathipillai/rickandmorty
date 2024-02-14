package com.ippon.rickandmorty.extensions

import com.apollographql.apollo3.api.Optional
import com.ippon.rickandmorty.apollo.type.FilterCharacter
import com.ippon.rickandmorty.extension.toFilterCharacter
import com.ippon.rickandmorty.models.Filter
import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.Status
import org.junit.Test
import kotlin.test.assertEquals

class FilterExtensionTest {
    @Test
    fun filterCharacterMapper_StatusAndGenderNull() {
        assertEquals(
            Filter(status = null, gender = null).toFilterCharacter(),
            FilterCharacter()
        )
    }

    @Test
    fun filterCharacterMapper_StatusNull() {
        assertEquals(
            Filter(status = Status.DEAD, gender = null).toFilterCharacter(),
            FilterCharacter(status = Optional.present("DEAD"))
        )
    }

    @Test
    fun filterCharacterMapper_GenderNull() {
        assertEquals(
            Filter(status = null, gender = Gender.GENDERLESS).toFilterCharacter(),
            FilterCharacter(gender = Optional.present("GENDERLESS"))
        )
    }

    @Test
    fun filterCharacterMapper_NonNull() {
        assertEquals(
            Filter(status = Status.DEAD, gender = Gender.GENDERLESS).toFilterCharacter(),
            FilterCharacter(
                status = Optional.present("DEAD"),
                gender = Optional.present("GENDERLESS")
            )
        )
    }
}