package com.danielriverolosa.data.repository.dto

import com.danielriverolosa.domain.entity.Location
import com.danielriverolosa.domain.entity.Status

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val location: Location,
    val image: String,
    val episodes: List<Int>,
    val page: Int
)