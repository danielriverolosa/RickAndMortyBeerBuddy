package com.danielriverolosa.data.repository

import com.danielriverolosa.data.datasource.api.character.CharacterApiDataSource
import com.danielriverolosa.data.datasource.local.character.CharacterLocalDataSource
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiDataSource: CharacterApiDataSource,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {

    override suspend fun getCharacterList(): List<Character> {
        val localList = localDataSource.getCharacterList()
        return if (localList.isNotEmpty()) {
            return localList
        } else getCharacterListPage()
    }

    override suspend fun getCharacterListPage(): List<Character> {
        val page = localDataSource.getLastPage() + 1
        apiDataSource.getCharacters(page).run {
            localDataSource.saveCharacters(this)
        }
        return localDataSource.getCharacterList()
    }
}