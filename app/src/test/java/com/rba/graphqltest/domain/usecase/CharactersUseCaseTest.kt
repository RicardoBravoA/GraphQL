package com.rba.graphqltest.domain.usecase

import com.rba.graphqltest.base.BaseMockk
import com.rba.graphqltest.data.repository.CharactersRepository
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

class CharactersUseCaseTest : BaseMockk() {

    @MockK
    private lateinit var repository: CharactersRepository

    @InjectMockKs
    lateinit var useCase: CharactersUseCase

    private val page = 1

    @Test
    fun `given params when call invoke then return success result`() = runBlocking {
        // given
        val expected = mockk<List<CharacterModel>>()

        coEvery { repository.getCharacters(page) } returns ResultType.Success(expected)

        // when
        val result = useCase(page)
        result as ResultType.Success

        // then
        assertEquals(expected, result.value)
    }

    @Test
    fun `given params when call invoke then return failure result`() = runBlocking {
        // given
        val expected = mockk<Failure>()

        coEvery { repository.getCharacters(page) } returns ResultType.Error(expected)

        // when
        val result = useCase(page)
        result as ResultType.Error

        // then
        assertEquals(expected, result.value)
    }

}
