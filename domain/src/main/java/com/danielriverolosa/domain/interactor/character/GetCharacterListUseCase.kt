package com.danielriverolosa.domain.interactor.character

import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.interactor.UseCase
import com.danielriverolosa.domain.repository.CharacterRepository

class GetCharacterListUseCase(
    private val repository: CharacterRepository
): UseCase<List<Character>, Boolean>() {

    override suspend fun run(fromStart: Boolean): List<Character> {
        return if (fromStart) {
            repository.getCharacterList()
        } else {
            repository.getCharacterListPage()
        }
    }
}