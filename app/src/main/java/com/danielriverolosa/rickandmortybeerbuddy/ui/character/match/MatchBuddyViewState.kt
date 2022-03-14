package com.danielriverolosa.rickandmortybeerbuddy.ui.character.match

import com.danielriverolosa.domain.entity.BeerBuddy
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.ViewState
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.CharacterUiModel

sealed class MatchBuddyViewState : ViewState {
    class InitializeView(val character: CharacterUiModel) : MatchBuddyViewState()
    class ShowBuddyBeer(val matched: BeerBuddy) : MatchBuddyViewState()
    class ShowError(val message: String) : MatchBuddyViewState()
}
