package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.rickandmortybeerbuddy.R
import com.danielriverolosa.rickandmortybeerbuddy.databinding.CharacterListViewBinding
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseFragment
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.Initialize
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListEvent.LoadNextPage
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.list.CharacterListViewState.*
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.toUiModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.utils.endless
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListView :
    BaseFragment<CharacterListViewBinding, CharacterListViewModel, CharacterListViewState>() {

    override val viewModel: CharacterListViewModel by viewModels()

    private val listAdapter by lazy { CharacterListAdapter(::showBuddyBeerFinder) }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = CharacterListViewBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(Initialize)
    }

    override fun onResume() {
        super.onResume()
        binding.searchViewEditText.text?.clear()
    }

    override fun render(viewState: CharacterListViewState) = when (viewState) {
        is InitializeView -> configureView()
        is ShowDataLoaded -> loadCharacterList(viewState.characters)
        is ShowError -> showError(binding.root, viewState.message)
    }

    private fun configureView() {
        binding.apply {
            configureSearchView()
            characterListView.apply {
                adapter = listAdapter
                endless {
                    if (searchViewEditText.text?.isEmpty() == true) {
                        viewModel.onEvent(LoadNextPage)
                    }
                }
            }
        }
    }

    private fun CharacterListViewBinding.configureSearchView() {
        searchView.typeface = ResourcesCompat.getFont(requireContext(), R.font.wubba_lubba_dub_dub)
        searchViewEditText.typeface =
            ResourcesCompat.getFont(requireContext(), R.font.wubba_lubba_dub_dub)

        searchView.setEndIconOnClickListener {
            searchViewEditText.text = null
        }

        searchViewEditText.doAfterTextChanged {
            val name = it.toString()
            if (name.isEmpty()) {
                listAdapter.resetList()
            } else {
                listAdapter.filterByName(name)
            }
        }
    }

    private fun loadCharacterList(characters: List<Character>) {
        listAdapter.loadData(characters)
    }

    private fun showBuddyBeerFinder(character: Character) {
        val action = CharacterListViewDirections.findBuddyBeer(character.toUiModel())
        findNavController().navigate(action)
    }
}