package com.rba.graphqltest.data.repository

import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel

fun interface CharactersRepository {

    suspend fun getCharacters(page: Int): ResultType<List<CharacterModel>, Failure>

}
