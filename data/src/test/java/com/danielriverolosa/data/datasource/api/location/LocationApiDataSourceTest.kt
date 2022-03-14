package com.danielriverolosa.data.datasource.api.location

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.character.model.LocationResponse
import com.danielriverolosa.data.utils.TestUtils
import com.danielriverolosa.domain.error.BadRequestError
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class LocationApiDataSourceTest : TestUtils() {

    @InjectMockKs
    private lateinit var dataSource: LocationApiDataSource

    @MockK
    private lateinit var clientGenerator: ApiClientGenerator

    @MockK
    private lateinit var api: RickAndMortyApi

    @Test
    fun `getLocation when response is success should map to domain correctly`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getLocation(any()) } returns Response.success(buildLocationResponse())

        val location = dataSource.getLocation(0)

        location.name shouldBe "name"
        location.id shouldBe 1
        location.residents[0] shouldBe 2
    }

    @Test
    fun `getLocation when response is null should throw exception`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getLocation(any()) } returns Response.success(null)

        shouldThrow<BadRequestError> {
            dataSource.getLocation(0)
        }
    }

    private fun buildLocationResponse() = LocationResponse(
        "name",
        "/1",
        listOf("/2")
    )

}