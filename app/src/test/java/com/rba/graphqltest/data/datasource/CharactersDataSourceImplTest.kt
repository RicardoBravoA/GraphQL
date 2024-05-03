package com.rba.graphqltest.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.testing.enqueueTestResponse
import com.rba.graphqltest.GetCharactersQuery
import com.rba.graphqltest.base.BaseDataSource
import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ApolloExperimental::class)
class CharactersDataSourceImplTest : BaseDataSource() {

    private lateinit var dataSource: CharactersDataSourceImpl
    private val page = 1

    override fun setUp() {
        super.setUp()
        dataSource = CharactersDataSourceImpl(apolloClient)
    }

    @Test
    fun `given params when call getCharacters then return success result`() = runBlocking {
        //given
        val query = GetCharactersQuery(page)
        val response = mockk<GetCharactersQuery.Characters>(relaxed = true)

        val data = GetCharactersQuery.Data(characters = response)
        val expected = emptyList<CharacterModel>()

        apolloClient.enqueueTestResponse(operation = query, data = data)

        //when
        val result = dataSource.getCharacters(page)
        result as ResultType.Success

        //then
        assertEquals(expected, result.value)
    }

    @Test
    fun `given params when call getCharacters then return server error result`() = runBlocking {
        //given

        val apolloClientMock = mockk<ApolloClient>()
        dataSource = CharactersDataSourceImpl(apolloClientMock)

        val responseMock = mockk<ApolloResponse<GetCharactersQuery.Data>>(relaxed = true)
        val expected = Failure.ServerError

        every { responseMock.hasErrors() } returns true

        coEvery { apolloClientMock.query(GetCharactersQuery(page)).execute() } returns responseMock


        //when
        val result = dataSource.getCharacters(page)
        result as ResultType.Error

        //then
        assertEquals(expected, result.value)
    }

    @Test
    fun `given params when call getCharacters and get null response then return unknown error result`() =
        runBlocking {
            //given
            val query = GetCharactersQuery(page)

            val data = GetCharactersQuery.Data(characters = null)
            val expected = Failure.UnknownError

            apolloClient.enqueueTestResponse(operation = query, data = data)

            //when
            val result = dataSource.getCharacters(page)
            result as ResultType.Error

            //then
            assertEquals(expected, result.value)
        }

    @Test
    fun `given params when call getCharacters then return unknown error result`() = runBlocking {
        //given
        val query = GetCharactersQuery(page)
        val expected = Failure.UnknownError

        apolloClient.enqueueTestResponse(operation = query, data = null)

        //when
        val result = dataSource.getCharacters(page)
        result as ResultType.Error

        //then
        assertEquals(expected, result.value)
    }

    @Test
    fun `given params when call getCharacters then return network error result`() = runBlocking {
        //given
        val expected = Failure.NetworkError

        //when
        val result = dataSource.getCharacters(page)
        result as ResultType.Error

        //then
        assertEquals(expected, result.value)
    }

}
