package com.ippon.rickandmorty.extension

import com.ippon.rickandmorty.models.Gender
import com.ippon.rickandmorty.models.Status

fun String.toStatus(): Status? {
    return when (this.lowercase()) {
        "alive" -> Status.ALIVE
        "dead" -> Status.DEAD
        "unknown" -> Status.UNKNOWN
        else -> null
    }
}

fun String.toGender(): Gender? {
    return when (this.lowercase()) {
        "female" -> Gender.FEMALE
        "male" -> Gender.MALE
        "genderless" -> Gender.GENDERLESS
        "unknown" -> Gender.UNKNOWN
        else -> null
    }
}