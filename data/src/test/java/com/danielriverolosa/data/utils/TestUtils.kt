package com.danielriverolosa.data.utils

import com.danielriverolosa.domain.dispatcher.DefaultDispatcherProvider
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

open class TestUtils {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(DefaultDispatcherProvider().unconfined)
    }

    @After
    fun clean() {
        clearAllMocks()
    }
}