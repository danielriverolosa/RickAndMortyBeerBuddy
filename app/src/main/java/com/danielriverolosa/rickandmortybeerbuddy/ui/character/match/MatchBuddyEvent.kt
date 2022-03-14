package com.danielriverolosa.rickandmortybeerbuddy.ui.character.match

import com.danielriverolosa.rickandmortybeerbuddy.ui.base.Event
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.CharacterUiModel

sealed class MatchBuddyEvent: Event {
    class Initialize(val character: CharacterUiModel): MatchBuddyEvent()
}