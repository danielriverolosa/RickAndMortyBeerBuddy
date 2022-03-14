package com.danielriverolosa.data.datasource.api.character

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.utils.handleResponse
import com.danielriverolosa.data.repository.dto.CharacterDto
import com.danielriverolosa.data.repository.mapper.toDomain
import com.danielriverolosa.data.repository.mapper.toDto
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.error.BadRequestError
import javax.inject.Inject

class CharacterApiDataSource @Inject constructor(
    private val clientGenerator: ApiClientGenerator
) {

    suspend fun getCharacters(page: Int): List<CharacterDto> {
        val api = clientGenerator.generate(RickAndMortyApi::class)
        return api.getCharacterList(page).handleResponse { response ->
            response?.results?.toDto(page) ?: emptyList()
        }
    }

    suspend fun getCharacters(ids: List<Int>): List<CharacterDto> {
        val api = clientGenerator.generate(RickAndMortyApi::class)
        return api.getCharacterList(ids).handleResponse { response ->
            response?.toDto(0) ?: emptyList()
        }
    }

    suspend fun getCharacter(id: Int): CharacterDto {
        val api = clientGenerator.generate(RickAndMortyApi::class)
        return api.getCharacterById(id).handleResponse { response ->
            response?.results?.firstOrNull()?.toDto(0) ?: throw BadRequestError()
        }
    }
}