package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.rickandmortybeerbuddy.databinding.CharacterListViewBinding
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseFragment
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.Initialize
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.LoadNextPage
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState.*
import com.danielriverolosa.rickandmortybeerbuddy.ui.utils.endless
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListView : BaseFragment<CharacterListViewBinding, CharacterListViewModel, CharacterListViewState>() {

    override val viewModel: CharacterListViewModel by viewModels()

    private val listAdapter by lazy { CharacterListAdapter() }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CharacterListViewBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(Initialize)
    }

    override fun render(viewState: CharacterListViewState) = when(viewState) {
        is InitializeView -> configureView()
        is ShowDataLoaded -> loadCharacterList(viewState.characters)
        is ShowError -> showError(binding.root, viewState.message)
    }

    private fun configureView() {
        binding.apply {
            characterListView.apply {
                adapter = listAdapter
                endless { viewModel.onEvent(LoadNextPage) }
            }
        }
    }

    private fun loadCharacterList(characters: List<Character>) {
        listAdapter.submitList(characters)
    }
}