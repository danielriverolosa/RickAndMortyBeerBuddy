package com.danielriverolosa.rickandmortybeerbuddy.ui.character.match

import com.danielriverolosa.domain.fold
import com.danielriverolosa.domain.interactor.buddy.GetBeerBuddyUseCase
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseViewModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.CharacterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchBuddyViewModel @Inject constructor(
    private val getBeerBuddyUseCase: GetBeerBuddyUseCase
) : BaseViewModel<MatchBuddyViewState, MatchBuddyEvent>() {

    override fun onEvent(event: MatchBuddyEvent) = when (event) {
        is MatchBuddyEvent.Initialize -> findBestBuddyBeer(event.character)
    }

    private fun findBestBuddyBeer(character: CharacterUiModel) {
        updateViewState(MatchBuddyViewState.InitializeView(character))
        launch {
            getBeerBuddyUseCase(character.id).fold(
                onSuccess = { updateViewState(MatchBuddyViewState.ShowBuddyBeer(it)) },
                onFailure = { updateViewState(MatchBuddyViewState.ShowError(it.message.toString())) }
            )
        }
    }
}