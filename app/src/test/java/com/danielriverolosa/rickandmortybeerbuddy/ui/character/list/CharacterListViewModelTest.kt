package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import app.cash.turbine.test
import com.danielriverolosa.domain.error.DomainException
import com.danielriverolosa.domain.failureOf
import com.danielriverolosa.domain.interactor.character.GetCharacterListUseCase
import com.danielriverolosa.domain.successOf
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.Initialize
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.LoadNextPage
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState.*
import com.danielriverolosa.rickandmortybeerbuddy.utils.TestUtils
import io.kotest.matchers.should
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterListViewModelTest: TestUtils() {

    @InjectMockKs
    private lateinit var viewModel: CharacterListViewModel

    @MockK
    private lateinit var useCase: GetCharacterListUseCase

    @Test
    fun `onEvent when Initialize should InitializeView and ShowDataLoaded`() = runBlockingTest {
        coEvery { useCase(any()) } returns successOf(listOf())
        viewModel.viewState.test {
            viewModel.onEvent(Initialize)

            awaitItem()
            awaitItem() should instanceOf<InitializeView>()
            awaitItem() should instanceOf<ShowDataLoaded>()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onEvent when LoadNextPage should ShowDataLoaded`() = runBlockingTest {
        coEvery { useCase(any()) } returns successOf(listOf())
        viewModel.viewState.test {
            viewModel.onEvent(LoadNextPage)

            awaitItem()
            awaitItem() should instanceOf<ShowDataLoaded>()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onEvent when use case result is failure should ShowError`() = runBlockingTest {
        coEvery { useCase(any()) } returns failureOf(DomainException("error"))
        viewModel.viewState.test {
            viewModel.onEvent(LoadNextPage)

            awaitItem()
            awaitItem() should instanceOf<ShowError>()

            cancelAndIgnoreRemainingEvents()
        }
    }

}