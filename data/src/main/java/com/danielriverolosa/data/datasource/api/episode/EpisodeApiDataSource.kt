package com.danielriverolosa.data.datasource.api.episode

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.utils.handleResponse
import com.danielriverolosa.data.repository.mapper.toEpisodeDomain
import com.danielriverolosa.domain.entity.Episode
import javax.inject.Inject

class EpisodeApiDataSource @Inject constructor(
    private val clientGenerator: ApiClientGenerator
) {

    suspend fun getEpisodes(ids: List<Int>): List<Episode> {
        val api = clientGenerator.generate(RickAndMortyApi::class)
        return api.getEpisodeList(ids).handleResponse { response ->
            response?.toEpisodeDomain() ?: emptyList()
        }
    }
}