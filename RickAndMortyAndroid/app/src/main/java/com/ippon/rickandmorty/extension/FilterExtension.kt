package com.ippon.rickandmorty.extension

import com.apollographql.apollo3.api.Optional
import com.ippon.rickandmorty.apollo.type.FilterCharacter
import com.ippon.rickandmorty.models.Filter

fun Filter.toFilterCharacter(): FilterCharacter {
    return FilterCharacter(
        status = Optional.presentIfNotNull(this.status?.name),
        gender = Optional.presentIfNotNull(this.gender?.name)
    )
}