package com.rba.graphqltest.data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.rba.graphqltest.data.util.BASE_URL
import com.rba.graphqltest.data.util.TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient)
        .build()

}
