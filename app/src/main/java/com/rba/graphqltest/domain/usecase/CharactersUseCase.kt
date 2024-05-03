package com.rba.graphqltest.domain.usecase

import com.rba.graphqltest.data.repository.CharactersRepository
import com.rba.graphqltest.data.util.Failure
import com.rba.graphqltest.data.util.ResultType
import com.rba.graphqltest.domain.model.CharacterModel
import javax.inject.Inject

class CharactersUseCase @Inject constructor(private val repository: CharactersRepository) {

    suspend operator fun invoke(page: Int): ResultType<List<CharacterModel>, Failure> {
        return repository.getCharacters(page)
    }

}
