package com.danielriverolosa.data.repository

import com.danielriverolosa.data.datasource.api.episode.EpisodeApiDataSource
import com.danielriverolosa.domain.entity.Episode
import com.danielriverolosa.domain.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val apiDataSource: EpisodeApiDataSource
): EpisodeRepository {
    override suspend fun getEpisodesFromList(ids: List<Int>): List<Episode> {
        return apiDataSource.getEpisodes(ids)
    }
}