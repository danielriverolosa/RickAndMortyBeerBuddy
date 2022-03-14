package com.danielriverolosa.domain.interactor.buddy

import com.danielriverolosa.domain.entity.Character

class MatchedEpisodes(
    val character: Character,
    val count: Int,
    val diff: Int,
    val firstEpisode: Int,
    val lastEpisode: Int
) : Comparable<MatchedEpisodes> {
    override fun compareTo(other: MatchedEpisodes) =
        if (count == other.count && diff == other.diff) {
            other.character.id.compareTo(character.id)
        } else if (count == other.count && diff != other.diff) {
            diff.compareTo(other.diff)
        } else {
            count.compareTo(other.count)
        }
}