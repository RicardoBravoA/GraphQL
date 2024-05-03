package com.rba.graphqltest.presentation.characters

import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import com.rba.graphqltest.base.BaseViewModelTest
import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel
import com.rba.graphqltest.domain.usecase.CharactersUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest : BaseViewModelTest() {

    @InjectMockKs
    private lateinit var viewModel: CharactersViewModel

    @MockK
    private lateinit var useCase: CharactersUseCase

    @Test
    fun `given useCase when call getPopularMovies then return success result`() = runTest {
        //given
        val response = mockk<List<CharacterModel>>(relaxed = true)

        coEvery { useCase(any()) } returns ResultType.Success(response)

        //when
        viewModel.getCharacters()

        //then
        assertEquals(
            response,
            (viewModel.viewState.value as CharactersViewModel.ViewState.Data).data
        )

    }

    @Test
    fun `given useCase when call getPopularMovies then return error result`() = runTest {
        //given
        val response = Failure.NetworkError

        coEvery { useCase(any()) } returns ResultType.Error(response)

        //when
        viewModel.getCharacters()

        //then
        assertEquals(
            response.message,
            (viewModel.viewState.value as CharactersViewModel.ViewState.Error).message
        )
    }

}
