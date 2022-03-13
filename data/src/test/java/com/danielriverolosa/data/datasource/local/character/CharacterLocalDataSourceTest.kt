package com.danielriverolosa.data.datasource.local.character

import com.danielriverolosa.data.datasource.local.character.model.CharacterDbEntity
import com.danielriverolosa.data.datasource.local.character.model.SimpleLocationDbEntity
import com.danielriverolosa.data.repository.dto.CharacterDto
import com.danielriverolosa.data.utils.TestUtils
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.entity.Location
import com.danielriverolosa.domain.entity.Status
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterLocalDataSourceTest : TestUtils() {

    @InjectMockKs
    private lateinit var dataSource: CharacterLocalDataSource

    @MockK
    private lateinit var dao: CharacterDao

    @Test
    fun `getCharacterList should map to domain`() = runTest {
        coEvery { dao.getAll() } returns listOf(buildCharacterDbEntity())

        val list = dataSource.getCharacterList()

        list shouldNotBe emptyList<Character>()
        list[0].id shouldBe 0
        list[0].name shouldBe "name"
        list[0].status shouldBe Status.ALIVE
        list[0].species shouldBe "species"
        list[0].type shouldBe "type"
        list[0].location.id shouldBe 1
        list[0].location.name shouldBe "name"
        list[0].image shouldBe "image"
        list[0].episodes[0] shouldBe 2
    }

    @Test
    fun `saveCharacters should map to db entity`() = runTest {
        val dbEntities = slot<List<CharacterDbEntity>>()
        coEvery { dao.insertAll(capture(dbEntities)) } just Runs

        dataSource.saveCharacters(listOf(buildCharacterDto()))

        dbEntities.captured shouldNotBe emptyList<CharacterDbEntity>()
        dbEntities.captured[0].id shouldBe 0
        dbEntities.captured[0].name shouldBe "name"
        dbEntities.captured[0].status shouldBe Status.ALIVE
        dbEntities.captured[0].species shouldBe "species"
        dbEntities.captured[0].type shouldBe "type"
        dbEntities.captured[0].location.id shouldBe 1
        dbEntities.captured[0].location.name shouldBe "name"
        dbEntities.captured[0].image shouldBe "image"
        dbEntities.captured[0].episodes[0] shouldBe 2
        dbEntities.captured[0].page shouldBe 3
    }

    @Test
    fun `getLastPage should get page from dao`() = runTest {
        coEvery { dao.getLastPage() } returns 0

        dataSource.getLastPage()

        coVerify { dao.getLastPage() }
    }

    private fun buildCharacterDbEntity() = CharacterDbEntity(
        0,
        "name",
        Status.ALIVE,
        "species",
        "type",
        SimpleLocationDbEntity(1, "name"),
        "image",
        listOf(2),
        3
    )

    private fun buildCharacterDto() = CharacterDto(
        0,
        "name",
        Status.ALIVE,
        "species",
        "type",
        Location(1, "name"),
        "image",
        listOf(2),
        3
    )
}