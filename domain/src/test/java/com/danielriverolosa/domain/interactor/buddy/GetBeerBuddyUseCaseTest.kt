package com.danielriverolosa.domain.interactor.buddy

import com.danielriverolosa.domain.Result
import com.danielriverolosa.domain.entity.*
import com.danielriverolosa.domain.repository.CharacterRepository
import com.danielriverolosa.domain.repository.EpisodeRepository
import com.danielriverolosa.domain.repository.LocationRepository
import com.danielriverolosa.domain.utils.TestUtils
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetBeerBuddyUseCaseTest : TestUtils() {

    @InjectMockKs
    private lateinit var useCase: GetBeerBuddyUseCase

    @MockK
    private lateinit var characterRepository: CharacterRepository

    @MockK
    private lateinit var locationRepository: LocationRepository

    @MockK
    private lateinit var episodeRepository: EpisodeRepository

    @Test
    fun `run should build buddy beer calling different repositories`() = runTest {
        coEvery { characterRepository.getCharacter(any()) } returns buildCharacter()
        coEvery { locationRepository.getCharacterIdsFromLocation(any()) } returns listOf(1)
        coEvery { characterRepository.getCharacterList(any()) } returns listOf(buildCharacter())
        coEvery { episodeRepository.getEpisodesFromList(any()) } returns listOf(buildEpisode())

        val result = useCase(0)

        result shouldBe instanceOf<Result.Success<BeerBuddy>>()
        val buddy = (result as Result.Success<BeerBuddy>).value

        buddy.count shouldBe 1
    }

    private fun buildCharacter() = Character(
        0,
        "name",
        Status.ALIVE,
        "species",
        "type",
        Location(1, "name"),
        "image",
        listOf(3)
    )

    private fun buildEpisode() = Episode(
        3,
        "name",
        "12/12/12"
    )

}