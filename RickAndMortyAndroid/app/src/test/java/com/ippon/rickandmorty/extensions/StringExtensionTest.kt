package com.ippon.rickandmorty.extensions

import com.ippon.rickandmorty.extension.toGender
import com.ippon.rickandmorty.extension.toStatus
import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.Status
import org.junit.Test
import kotlin.test.assertEquals

class StringExtensionTest {
    @Test
    fun statusMapperTest() {
        assertEquals("alive".toStatus(), Status.ALIVE)
        assertEquals("dead".toStatus(), Status.DEAD)
        assertEquals("unknown".toStatus(), Status.UNKNOWN)
        assertEquals("test".toStatus(), null)
    }

    @Test
    fun genderMapperTest() {
        assertEquals("female".toGender(), Gender.FEMALE)
        assertEquals("male".toGender(), Gender.MALE)
        assertEquals("genderless".toGender(), Gender.GENDERLESS)
        assertEquals("unknown".toGender(), Gender.UNKNOWN)
        assertEquals("test".toGender(), null)
    }
}