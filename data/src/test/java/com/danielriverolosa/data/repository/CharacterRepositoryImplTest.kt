package com.danielriverolosa.data.repository

import com.danielriverolosa.data.datasource.api.character.CharacterApiDataSource
import com.danielriverolosa.data.datasource.local.character.CharacterLocalDataSource
import com.danielriverolosa.data.utils.TestUtils
import com.danielriverolosa.domain.entity.Character
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterRepositoryImplTest : TestUtils() {

    @InjectMockKs
    private lateinit var repository: CharacterRepositoryImpl

    @MockK
    lateinit var apiDataSource: CharacterApiDataSource

    @MockK
    lateinit var localDataSource: CharacterLocalDataSource

    @MockK
    private lateinit var character: Character

    @Test
    fun `getCharacterList when localDataSource is not empty should not call to apiDataSource`() = runTest {
        coEvery { localDataSource.getCharacterList() } returns listOf(character)

        repository.getCharacterList()

        coVerify(exactly = 0) { localDataSource.getLastPage() }
        coVerify(exactly = 0) { apiDataSource.getCharacters(any()) }
    }

    @Test
    fun `getCharacterList when localDataSource is empty should call to apiDataSource`() = runTest {
        coEvery { localDataSource.getCharacterList() } returns listOf()
        coEvery { apiDataSource.getCharacters(any()) } returns listOf()
        coEvery { localDataSource.getLastPage() } returns 0
        coEvery { localDataSource.saveCharacters(any()) } just Runs

        repository.getCharacterList()

        coVerify { localDataSource.getLastPage() }
        coVerify { apiDataSource.getCharacters(any()) }
        coVerify { localDataSource.saveCharacters(any()) }
    }
}