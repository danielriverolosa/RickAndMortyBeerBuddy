package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import com.danielriverolosa.domain.fold
import com.danielriverolosa.domain.interactor.character.GetCharacterListUseCase
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseViewModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.Initialize
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.LoadNextPage
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel<CharacterListViewState, CharacterListEvent>() {
    override fun onEvent(event: CharacterListEvent) = when (event) {
        is Initialize -> initializeView()
        is LoadNextPage -> loadData()
    }

    private fun initializeView() {
        updateViewState(InitializeView)
        loadData(true)
    }

    private fun loadData(fromStart: Boolean = false) {
        launch {
            getCharacterListUseCase(fromStart).fold(
                onSuccess = { updateViewState(ShowDataLoaded(it)) },
                onFailure = { updateViewState(ShowError(it.message.toString())) }
            )
        }
    }
}