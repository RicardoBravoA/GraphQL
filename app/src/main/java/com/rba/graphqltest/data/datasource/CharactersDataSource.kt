package com.rba.graphqltest.data.datasource

import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel

fun interface CharactersDataSource {

    suspend fun getCharacters(page: Int): ResultType<List<CharacterModel>, Failure>

}
