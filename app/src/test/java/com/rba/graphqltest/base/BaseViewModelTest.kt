package com.rba.graphqltest.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseViewModelTest : BaseMockk() {

    @get:Rule
    val executeLiveDataInstantly = InstantTaskExecutorRule()

    @Before
    override fun setUp() {
        super.setUp()
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
    }

}
