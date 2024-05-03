package com.rba.graphqltest.data.repository.di

import com.rba.graphqltest.data.repository.CharactersRepository
import com.rba.graphqltest.data.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

}
