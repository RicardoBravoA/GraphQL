package com.rba.graphqltest.data.repository

import com.rba.graphqltest.base.BaseMockk
import com.rba.graphqltest.data.datasource.CharactersDataSource
import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersRepositoryImplTest : BaseMockk() {

    @InjectMockKs
    private lateinit var repository: CharactersRepositoryImpl

    @MockK
    lateinit var dataSource: CharactersDataSource

    private val page = 1

    @Test
    fun `given params when call invoke then return success result`() = runBlocking {
        // given
        val expected = mockk<List<CharacterModel>>()

        coEvery { dataSource.getCharacters(page) } returns ResultType.Success(expected)

        // when
        val result = repository.getCharacters(page)
        result as ResultType.Success

        // then
        assertEquals(expected, result.value)
    }

    @Test
    fun `given params when call invoke then return failure result`() = runBlocking {
        // given
        val expected = mockk<Failure>()

        coEvery { dataSource.getCharacters(page) } returns ResultType.Error(expected)

        // when
        val result = repository.getCharacters(page)
        result as ResultType.Error

        // then
        assertEquals(expected, result.value)
    }

}
