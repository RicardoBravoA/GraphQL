package com.rba.graphqltest.domain.model

data class CharacterModel(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)
