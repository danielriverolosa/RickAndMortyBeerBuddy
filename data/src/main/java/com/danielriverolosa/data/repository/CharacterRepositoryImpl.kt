package com.danielriverolosa.data.repository

import com.danielriverolosa.data.datasource.api.character.CharacterApiDataSource
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApiDataSource: CharacterApiDataSource
) : CharacterRepository {

    override suspend fun getCharacterList(page: Int): List<Character> {
        return characterApiDataSource.getCharacters(page)
    }
}