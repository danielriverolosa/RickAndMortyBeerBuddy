package com.danielriverolosa.data.datasource.api.episode

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.episode.model.EpisodeResponse
import com.danielriverolosa.data.utils.TestUtils
import com.danielriverolosa.domain.entity.Episode
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class EpisodeApiDataSourceTest : TestUtils() {

    @InjectMockKs
    private lateinit var dataSource: EpisodeApiDataSource

    @MockK
    private lateinit var clientGenerator: ApiClientGenerator

    @MockK
    private lateinit var api: RickAndMortyApi

    @Test
    fun `getEpisodeList when response is success should map to domain correctly`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getEpisodeList(any()) } returns Response.success(listOf(buildEpisodeResponse()))

        val episodes = dataSource.getEpisodes(listOf())

        episodes shouldNotBe emptyList<Episode>()

        episodes[0].id shouldBe 0
        episodes[0].name shouldBe "name"
        episodes[0].date shouldBe "12/12/12"
    }

    @Test
    fun `getEpisodeList when response is null should throw exception`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getEpisodeList(any()) } returns Response.success(null)

        val episodes = dataSource.getEpisodes(listOf(1))

        episodes shouldBe emptyList()
    }

    private fun buildEpisodeResponse() = EpisodeResponse(
        0,
        "name",
        "123",
        "12/12/12"
    )
}