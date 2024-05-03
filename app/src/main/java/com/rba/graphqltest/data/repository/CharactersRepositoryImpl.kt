package com.rba.graphqltest.data.repository

import com.rba.graphqltest.data.datasource.CharactersDataSource
import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val dataSource: CharactersDataSource) :
    CharactersRepository {

    override suspend fun getCharacters(page: Int): ResultType<List<CharacterModel>, Failure> {
        return dataSource.getCharacters(page)
    }

}
