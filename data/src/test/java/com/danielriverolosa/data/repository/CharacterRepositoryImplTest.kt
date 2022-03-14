package com.danielriverolosa.data.repository

import com.danielriverolosa.data.datasource.api.character.CharacterApiDataSource
import com.danielriverolosa.data.datasource.local.character.CharacterLocalDataSource
import com.danielriverolosa.data.repository.dto.CharacterDto
import com.danielriverolosa.data.utils.TestUtils
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.entity.Location
import com.danielriverolosa.domain.entity.Status
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
        coVerify(exactly = 0) { apiDataSource.getCharacters(any<Int>()) }
    }

    @Test
    fun `getCharacterList when localDataSource is empty should call to apiDataSource`() = runTest {
        coEvery { localDataSource.getCharacterList() } returns listOf()
        coEvery { apiDataSource.getCharacters(any<Int>()) } returns listOf()
        coEvery { localDataSource.getLastPage() } returns 0
        coEvery { localDataSource.saveCharacters(any()) } just Runs

        repository.getCharacterList()

        coVerify { localDataSource.getLastPage() }
        coVerify { apiDataSource.getCharacters(any<Int>()) }
        coVerify { localDataSource.saveCharacters(any()) }
    }

    @Test
    fun `getCharacterListByIds should call to apiDataSource`() = runTest {
        coEvery { apiDataSource.getCharacters(any<List<Int>>()) } returns listOf()

        repository.getCharacterList(listOf())

        coVerify { apiDataSource.getCharacters(any<List<Int>>()) }
    }

    @Test
    fun `getCharacter when localDataSource is empty should call to apiDataSource`() = runTest {
        coEvery { localDataSource.getCharacter(any()) } returns null
        coEvery { apiDataSource.getCharacter(any()) } returns buildCharacterDto()

        repository.getCharacter(0)

        coVerify { localDataSource.getCharacter(any()) }
        coVerify { apiDataSource.getCharacter(any()) }
    }

    @Test
    fun `getCharacter when localDataSource has data should not call to apiDataSource`() = runTest {
        coEvery { localDataSource.getCharacter(any()) } returns character

        repository.getCharacter(0)

        coVerify { localDataSource.getCharacter(any()) }
        coVerify(exactly = 0) { apiDataSource.getCharacter(any()) }
    }

    private fun buildCharacterDto() = CharacterDto(
        0,
        "name",
        Status.ALIVE,
        "species",
        "type",
        Location(1, "name"),
        "image",
        listOf(),
        0
    )
}