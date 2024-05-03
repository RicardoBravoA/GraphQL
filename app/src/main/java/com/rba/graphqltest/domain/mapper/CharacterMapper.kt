package com.rba.graphqltest.domain.mapper

import com.rba.graphqltest.GetCharactersQuery
import com.rba.graphqltest.domain.model.CharacterModel

fun GetCharactersQuery.Characters.toDomain(): List<CharacterModel> {
    return results?.mapNotNull { it?.toDomain() } ?: emptyList()
}

fun GetCharactersQuery.Result.toDomain() =
    CharacterModel(id ?: "", name ?: "", status ?: "", species ?: "", gender ?: "", image ?: "")
