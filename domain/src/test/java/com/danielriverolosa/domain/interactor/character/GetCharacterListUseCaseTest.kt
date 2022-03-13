package com.danielriverolosa.domain.interactor.character

import com.danielriverolosa.domain.utils.TestUtils
import com.danielriverolosa.domain.repository.CharacterRepository
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterListUseCaseTest : TestUtils() {

    @InjectMockKs
    private lateinit var useCase: GetCharacterListUseCase

    @MockK
    private lateinit var repository: CharacterRepository

    @Test
    fun `run when fromStart is true should call to getCharacterList`() = runTest {
        useCase(true)

        coVerify { repository.getCharacterList() }
    }

    @Test
    fun `run when fromStart is false should call to getCharacterListPage`() = runTest {
        useCase(false)

        coVerify { repository.getCharacterListPage() }
    }
}