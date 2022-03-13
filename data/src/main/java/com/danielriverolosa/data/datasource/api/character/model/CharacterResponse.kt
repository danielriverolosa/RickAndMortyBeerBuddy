package com.danielriverolosa.data.datasource.api.character.model

import com.squareup.moshi.Json

data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val location: LocationResponse,
    val image: String,
    @field:Json(name = "episode") val episodes: List<String>
)