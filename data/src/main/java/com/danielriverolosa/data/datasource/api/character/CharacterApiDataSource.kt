package com.danielriverolosa.data.datasource.api.character

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.utils.handleResponse
import com.danielriverolosa.data.repository.mapper.toDomain
import com.danielriverolosa.domain.entity.Character
import javax.inject.Inject

class CharacterApiDataSource @Inject constructor(
    private val clientGenerator: ApiClientGenerator
) {

    suspend fun getCharacters(page: Int): List<Character> {
        val api = clientGenerator.generate(RickAndMortyApi::class)
        return api.getCharacterList(page).handleResponse { response ->
            response?.let { body -> body.results.map { it.toDomain() } } ?: emptyList()
        }
    }
}