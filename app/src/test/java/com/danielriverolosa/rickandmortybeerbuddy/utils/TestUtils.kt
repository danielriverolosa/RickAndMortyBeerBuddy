package com.danielriverolosa.rickandmortybeerbuddy.utils

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
open class TestUtils {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun clean() {
        clearAllMocks()
    }
}