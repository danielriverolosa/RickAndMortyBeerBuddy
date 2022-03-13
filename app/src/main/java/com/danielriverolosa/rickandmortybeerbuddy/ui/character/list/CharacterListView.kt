package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseFragment
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.Initialize
import com.danielriverolosa.rickandmortybeerbuddy.databinding.CharacterListViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListView : BaseFragment<CharacterListViewBinding, CharacterListViewModel, CharacterListViewState>() {

    override val viewModel: CharacterListViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CharacterListViewBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(Initialize)
    }

    override fun render(viewState: CharacterListViewState) = when(viewState) {
        is CharacterListViewState.ConfigureView -> configureView(viewState.characters)
        is CharacterListViewState.ShowError -> showError(binding.root, viewState.message)
    }

    private fun configureView(characters: List<Character>) {
        binding.apply {
            characterListView.apply {
                adapter = CharacterListAdapter().apply {
                    submitList(characters)
                }
            }
        }
    }
}