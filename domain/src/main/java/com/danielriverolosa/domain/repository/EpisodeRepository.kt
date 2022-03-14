package com.danielriverolosa.domain.repository

import com.danielriverolosa.domain.entity.Episode

interface EpisodeRepository {
    suspend fun getEpisodesFromList(ids: List<Int>) : List<Episode>
}