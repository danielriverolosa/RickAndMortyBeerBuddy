package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import com.danielriverolosa.rickandmortybeerbuddy.ui.base.Event

sealed class CharacterListEvent : Event {
    object Initialize: CharacterListEvent()
    object LoadNextPage: CharacterListEvent()
}
