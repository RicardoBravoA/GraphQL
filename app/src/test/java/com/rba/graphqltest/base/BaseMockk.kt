package com.rba.graphqltest.base

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

open class BaseMockk {

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    open fun tearDown() {
        unmockkAll()
    }

}
