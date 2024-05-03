package com.rba.graphqltest.data.util

sealed class ResultType<S, E> {
    data class Success<S, E>(val value: S) : ResultType<S, E>()
    data class Error<S, E>(val value: E) : ResultType<S, E>()
}
