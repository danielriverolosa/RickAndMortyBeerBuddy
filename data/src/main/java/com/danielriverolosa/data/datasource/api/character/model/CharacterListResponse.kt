package com.danielriverolosa.data.datasource.api.character.model

data class CharacterListResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)