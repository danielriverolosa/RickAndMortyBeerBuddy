package com.danielriverolosa.data.datasource.local.character

import com.danielriverolosa.data.repository.dto.CharacterDto
import com.danielriverolosa.data.repository.mapper.toDbEntity
import com.danielriverolosa.data.repository.mapper.toDomain
import com.danielriverolosa.domain.entity.Character
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend fun getCharacterList(): List<Character> =
        characterDao.getAll().map { it.toDomain() }

    suspend fun saveCharacters(characterList: List<CharacterDto>) {
        characterDao.insertAll(characterList.toDbEntity())
    }

    suspend fun getLastPage() = characterDao.getLastPage()
}