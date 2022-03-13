package com.danielriverolosa.domain.repository

import com.danielriverolosa.domain.entity.Character

interface CharacterRepository {
    suspend fun getCharacterList(page: Int): List<Character>
}