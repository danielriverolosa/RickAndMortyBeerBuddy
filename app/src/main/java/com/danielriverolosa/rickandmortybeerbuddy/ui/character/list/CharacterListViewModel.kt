package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import com.danielriverolosa.domain.fold
import com.danielriverolosa.domain.interactor.character.GetCharacterListUseCase
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseViewModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.Initialize
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState.ConfigureView
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState.ShowError
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel<CharacterListViewState, CharacterListEvent>() {
    override fun onEvent(event: CharacterListEvent) = when (event) {
        is Initialize -> loadData()
    }

    private fun loadData() {
        launch {
            getCharacterListUseCase(1).fold(
                onSuccess = { updateViewState(ConfigureView(it)) },
                onFailure = { updateViewState(ShowError(it.message.toString())) }
            )
        }
    }
}