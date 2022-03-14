package com.danielriverolosa.data.datasource.api.character

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.character.model.CharacterListResponse
import com.danielriverolosa.data.datasource.api.character.model.CharacterResponse
import com.danielriverolosa.data.datasource.api.character.model.InfoResponse
import com.danielriverolosa.data.datasource.api.character.model.LocationResponse
import com.danielriverolosa.data.repository.dto.CharacterDto
import com.danielriverolosa.data.utils.TestUtils
import com.danielriverolosa.domain.entity.Status
import com.danielriverolosa.domain.error.BadRequestError
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CharacterApiDataSourceTest : TestUtils() {

    @InjectMockKs
    private lateinit var dataSource: CharacterApiDataSource

    @MockK
    private lateinit var clientGenerator: ApiClientGenerator

    @MockK
    private lateinit var api: RickAndMortyApi

    @Test
    fun `getCharacters when response is success should map to dto correctly`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getCharacterList(any<Int>()) } returns Response.success(buildCharacterListResponse())

        val characterList = dataSource.getCharacters(0)

        characterList shouldNotBe emptyList<CharacterDto>()
        characterList[0].id shouldBe 0
        characterList[0].name shouldBe "name"
        characterList[0].status shouldBe Status.ALIVE
        characterList[0].species shouldBe "species"
        characterList[0].type shouldBe "type"
        characterList[0].location.id shouldBe 0
        characterList[0].location.name shouldBe "name"
        characterList[0].image shouldBe "image"
        characterList[0].episodes[0] shouldBe 0
    }

    @Test
    fun `getCharacters when response is null should return empty list`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getCharacterList(any<Int>()) } returns Response.success(buildCharacterListResponse(false))

        val characterList = dataSource.getCharacters(0)

        characterList shouldBe emptyList()
    }

    @Test
    fun `getCharacters when response is not success should throw exception`() = runTest {
        coEvery<RickAndMortyApi> { clientGenerator.generate(any()) } returns api
        coEvery { api.getCharacterList(any<Int>()) } returns Response.error(400, "error".toResponseBody())

        shouldThrow<BadRequestError> {
            dataSource.getCharacters(0)
        }
    }

    private fun buildCharacterListResponse(hasData: Boolean = true) = CharacterListResponse(
        buildInfoResponse(),
        if (hasData) listOf(buildCharacterResponse()) else emptyList()
    )

    private fun buildInfoResponse() = InfoResponse(
        0,
        0,
        "0",
        "0"
    )

    private fun buildCharacterResponse() = CharacterResponse(
        0,
        "name",
        "ALIVE",
        "species",
        "type",
        "gender",
        LocationResponse("name", "/0"),
        "image",
        listOf("/0")
    )
}