package com.danielriverolosa.domain.interactor.character

import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.interactor.UseCase
import com.danielriverolosa.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repository: CharacterRepository
): UseCase<List<Character>, Int>() {

    override suspend fun run(params: Int): List<Character> {
        return repository.getCharacterList(params)
    }
}