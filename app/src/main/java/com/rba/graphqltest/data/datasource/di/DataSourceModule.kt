package com.rba.graphqltest.data.datasource.di

import com.rba.graphqltest.data.datasource.CharactersDataSource
import com.rba.graphqltest.data.datasource.CharactersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharactersDataSource(dataSource: CharactersDataSourceImpl): CharactersDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
