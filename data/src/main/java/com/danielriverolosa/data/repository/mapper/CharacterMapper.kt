package com.danielriverolosa.data.repository.mapper

import com.danielriverolosa.data.datasource.api.character.model.CharacterResponse
import com.danielriverolosa.data.datasource.api.character.model.LocationResponse
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.entity.Location
import java.util.*

fun CharacterResponse.toDomain() = Character(
    id,
    name,
    enumValueOf(status.uppercase(Locale.getDefault())),
    species,
    type,
    location.toDomain(),
    image,
    episodes.map { it.getIdFromUrl() }
)

private fun LocationResponse.toDomain() = Location(
    url.getIdFromUrl(),
    name
)

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0