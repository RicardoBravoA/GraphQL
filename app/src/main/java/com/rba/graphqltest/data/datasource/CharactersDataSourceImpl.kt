package com.rba.graphqltest.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.rba.graphqltest.GetCharactersQuery
import com.rba.graphqltest.data.datasource.di.IoDispatcher
import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.mapper.toDomain
import com.rba.graphqltest.domain.model.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharactersDataSource {

    override suspend fun getCharacters(page: Int): ResultType<List<CharacterModel>, Failure> {
        return withContext(dispatcher) {
            try {
                val result = apolloClient.query(GetCharactersQuery(page)).execute()

                if (result.hasErrors()) {
                    ResultType.Error(Failure.ServerError)
                } else {
                    result.data?.characters?.let {
                        ResultType.Success(it.toDomain())
                    } ?: run { ResultType.Error(Failure.UnknownError) }
                }

            } catch (_: Exception) {
                ResultType.Error(Failure.NetworkError)
            }
        }
    }

}
