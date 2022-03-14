package com.danielriverolosa.rickandmortybeerbuddy.ui.character.match

import com.danielriverolosa.domain.entity.BuddyBeer
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.ViewState
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.CharacterUiModel

sealed class MatchBuddyViewState : ViewState {
    class InitializeView(val character: CharacterUiModel) : MatchBuddyViewState()
    class ShowBuddyBeer(val matched: BuddyBeer) : MatchBuddyViewState()
    class ShowError(val message: String) : MatchBuddyViewState()
}
