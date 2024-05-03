package com.rba.graphqltest.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel
import com.rba.graphqltest.domain.usecase.CharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCase: CharactersUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    private val page = 1

    fun getCharacters() {
        viewModelScope.launch {

            _viewState.value = ViewState.Loading

            val result = useCase(page)

            if (result is ResultType.Success) {
                _viewState.value = ViewState.Data(result.value)
            }

            if (result is ResultType.Error) {
                _viewState.value = ViewState.Error(result.value.message)
            }
        }
    }

    sealed class ViewState {
        data class Data(val data: List<CharacterModel>) : ViewState()
        data class Error(val message: String) : ViewState()
        data object Loading : ViewState()
    }

}
