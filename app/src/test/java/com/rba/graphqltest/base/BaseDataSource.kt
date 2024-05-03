package com.rba.graphqltest.base

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.testing.QueueTestNetworkTransport

@OptIn(ApolloExperimental::class)
open class BaseDataSource : BaseMockk() {

    lateinit var apolloClient: ApolloClient

    override fun setUp() {
        super.setUp()

        apolloClient = ApolloClient.Builder()
            .networkTransport(QueueTestNetworkTransport())
            .build()
    }

}
