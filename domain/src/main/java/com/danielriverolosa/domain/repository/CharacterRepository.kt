package com.danielriverolosa.domain.repository

import com.danielriverolosa.domain.entity.Character

interface CharacterRepository {
    suspend fun getCharacterList(): List<Character>

    suspend fun getCharacterListPage(): List<Character>

    suspend fun getCharacterList(idList: List<Int>): List<Character>

    suspend fun getCharacter(id: Int) : Character
}