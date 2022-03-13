package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.ViewState

sealed class CharacterListViewState : ViewState {
    object InitializeView : CharacterListViewState()
    class ShowDataLoaded(val characters: List<Character>) : CharacterListViewState()
    class ShowError(val message: String) : CharacterListViewState()
}