package com.danielriverolosa.rickandmortybeerbuddy.ui.character.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.danielriverolosa.domain.entity.BuddyBeer
import com.danielriverolosa.rickandmortybeerbuddy.databinding.MatchBuddyViewBinding
import com.danielriverolosa.rickandmortybeerbuddy.ui.base.BaseFragment
import com.danielriverolosa.rickandmortybeerbuddy.ui.character.CharacterUiModel
import com.danielriverolosa.rickandmortybeerbuddy.ui.utils.hide
import com.danielriverolosa.rickandmortybeerbuddy.ui.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchBuddyView :
    BaseFragment<MatchBuddyViewBinding, MatchBuddyViewModel, MatchBuddyViewState>() {

    override val viewModel: MatchBuddyViewModel by viewModels()

    private val args: MatchBuddyViewArgs by navArgs()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = MatchBuddyViewBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(MatchBuddyEvent.Initialize(args.character))
    }

    override fun render(viewState: MatchBuddyViewState) = when (viewState) {
        is MatchBuddyViewState.InitializeView -> initializeView(viewState.character)
        is MatchBuddyViewState.ShowBuddyBeer -> loadBuddyData(viewState.matched)
        is MatchBuddyViewState.ShowError -> showError(viewState.message)
    }

    private fun initializeView(character: CharacterUiModel) {
        binding.apply {
            selectedCharacterImage.load(character.image)
            characterName.text = character.name
            backAction.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun loadBuddyData(matched: BuddyBeer) {
        binding.apply {
            matched.apply {
                buddyMatchedImage.load(buddy.image)
                buddyName.text = buddy.name

                locationText.text = character.location.name
                episodesText.text = count.toString()
                firstTimeText.text = firstEpisode.date
                lastTimeText.text = lastEpisode.date

                loadingGroup.hide()
                buddyMatchedImage.show()
                buddyGroup.show()
            }
        }
    }

    private fun showError(message: String) {
        binding.apply {
            loading.hide()
            loadingText.text = message
        }
    }
}