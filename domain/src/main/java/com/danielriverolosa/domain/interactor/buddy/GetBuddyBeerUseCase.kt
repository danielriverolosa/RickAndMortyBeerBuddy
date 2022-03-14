package com.danielriverolosa.domain.interactor.buddy

import com.danielriverolosa.domain.entity.BuddyBeer
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.error.DomainException
import com.danielriverolosa.domain.interactor.UseCase
import com.danielriverolosa.domain.repository.CharacterRepository
import com.danielriverolosa.domain.repository.EpisodeRepository
import com.danielriverolosa.domain.repository.LocationRepository
import javax.inject.Inject

class GetBuddyBeerUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository,
    private val episodeRepository: EpisodeRepository
) : UseCase<BuddyBeer, Int>() {

    companion object {
        private const val BUDDY_NOT_FOUND = "Buddy Beer Not Found"
    }

    override suspend fun run(params: Int): BuddyBeer {
        val character = characterRepository.getCharacter(params)
        val characterIds = locationRepository.getCharacterIdsFromLocation(character.location.id)
            .filterNot { it == character.id }
        val charactersFromLocation = getCharactersFromIds(characterIds)
        val bestBuddy = getBestBuddyBeer(character, charactersFromLocation)
        return bestBuddy ?: throw DomainException(BUDDY_NOT_FOUND)
    }

    private suspend fun getCharactersFromIds(characterIds: List<Int>): List<Character> {
        if (characterIds.isEmpty()) throw DomainException(BUDDY_NOT_FOUND)

        return characterRepository.getCharacterList(characterIds)
    }

    private suspend fun getBestBuddyBeer(
        selectedCharacter: Character,
        buddiesFromLocation: List<Character>
    ): BuddyBeer? {
        val matchedEpisodesList = buddiesFromLocation.mapNotNull { buddy ->
            buddy.mapToEpisodesResult(selectedCharacter)
        }.sortedDescending()

        return if (matchedEpisodesList.isNotEmpty()) buildBestBuddy(
            matchedEpisodesList,
            selectedCharacter
        ) else null
    }

    private suspend fun buildBestBuddy(
        matchedEpisodesList: List<MatchedEpisodes>,
        selectedCharacter: Character
    ): BuddyBeer {
        val bestBuddy = matchedEpisodesList.first()

        val episodes = episodeRepository.getEpisodesFromList(
            listOf(
                bestBuddy.firstEpisode,
                bestBuddy.lastEpisode
            )
        ).sortedBy { it.id }

        return BuddyBeer(
            bestBuddy.count,
            bestBuddy.character,
            selectedCharacter,
            episodes.first(),
            episodes.last()
        )
    }

    private fun Character.mapToEpisodesResult(
        selectedCharacter: Character
    ): MatchedEpisodes? {
        val filterEpisodes = episodes.filter {
            selectedCharacter.episodes.contains(it)
        }.sorted()

        return if (filterEpisodes.isEmpty()) null else buildMatchedEpisodeResult(
            this,
            filterEpisodes
        )
    }

    private fun buildMatchedEpisodeResult(
        character: Character,
        filterEpisodes: List<Int>
    ): MatchedEpisodes {
        val firstEpisode = filterEpisodes.first()
        val lastEpisode = filterEpisodes.last()

        return MatchedEpisodes(
            character,
            filterEpisodes.size,
            lastEpisode - firstEpisode,
            firstEpisode,
            lastEpisode
        )
    }
}