package com.danielriverolosa.rickandmortybeerbuddy.ui.character.match

import app.cash.turbine.test
import com.danielriverolosa.domain.entity.BuddyBeer
import com.danielriverolosa.domain.error.DomainException
import com.danielriverolosa.domain.failureOf
import com.danielriverolosa.domain.interactor.buddy.GetBuddyBeerUseCase
import com.danielriverolosa.domain.interactor.character.GetCharacterListUseCase
import com.danielriverolosa.domain.successOf
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.CharacterUiModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState
import com.danielriverolosa.rickandmortybeerbuddy.utils.TestUtils
import io.kotest.matchers.should
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class MatchBuddyViewModelTest: TestUtils() {

    @InjectMockKs
    private lateinit var viewModel: MatchBuddyViewModel

    @MockK
    private lateinit var useCase: GetBuddyBeerUseCase

    @MockK
    private lateinit var buddyBeer: BuddyBeer

    @MockK
    private lateinit var character: CharacterUiModel

    @Test
    fun `onEvent when Initialize should InitializeView and ShowDataLoaded`() = runBlockingTest {
        coEvery { useCase(any()) } returns successOf(buddyBeer)
        coEvery { character.id } returns 0
        viewModel.viewState.test {
            viewModel.onEvent(MatchBuddyEvent.Initialize(character))

            awaitItem()
            awaitItem() should instanceOf<MatchBuddyViewState.InitializeView>()
            awaitItem() should instanceOf<MatchBuddyViewState.ShowBuddyBeer>()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onEvent when use case result is failure should ShowError`() = runBlockingTest {
        coEvery { useCase(any()) } returns failureOf(DomainException("error"))
        coEvery { character.id } returns 0
        viewModel.viewState.test {
            viewModel.onEvent(MatchBuddyEvent.Initialize(character))

            awaitItem()
            awaitItem() should instanceOf<MatchBuddyViewState.InitializeView>()
            awaitItem() should instanceOf<MatchBuddyViewState.ShowError>()

            cancelAndIgnoreRemainingEvents()
        }
    }
}