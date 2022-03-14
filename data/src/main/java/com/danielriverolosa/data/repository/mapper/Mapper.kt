package com.danielriverolosa.data.repository.mapper

import com.danielriverolosa.data.datasource.api.character.model.CharacterResponse
import com.danielriverolosa.data.datasource.api.character.model.LocationResponse
import com.danielriverolosa.data.datasource.api.episode.model.EpisodeResponse
import com.danielriverolosa.data.datasource.local.character.model.CharacterDbEntity
import com.danielriverolosa.data.datasource.local.character.model.SimpleLocationDbEntity
import com.danielriverolosa.data.repository.dto.CharacterDto
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.domain.entity.Episode
import com.danielriverolosa.domain.entity.Location
import java.util.*

fun List<CharacterResponse>.toDto(page: Int) = map { it.toDto(page) }

fun CharacterResponse.toDto(page: Int) = CharacterDto(
    id,
    name,
    enumValueOf(status.uppercase(Locale.getDefault())),
    species,
    type,
    location.toDomain(),
    image,
    episodes.map { it.getIdFromUrl() },
    page
)

fun LocationResponse.toDomain() = Location(
    url.getIdFromUrl(),
    name,
    residents?.map { it.getIdFromUrl() }.orEmpty()
)

fun String.getIdFromUrl(): Int = substring(lastIndexOf("/") + 1).toIntOrNull() ?: 0

fun CharacterDbEntity.toDomain() = Character(
    id,
    name,
    status,
    species,
    type,
    location.toDomain(),
    image,
    episodes
)

fun SimpleLocationDbEntity.toDomain() = Location(
    id,
    name.orEmpty()
)

fun List<CharacterDto>.toDbEntity() = map { it.toDbEntity() }

fun CharacterDto.toDbEntity() = CharacterDbEntity(
    id,
    name,
    status,
    species,
    type,
    location.toDbEntity(),
    image,
    episodes,
    page
)

fun Location.toDbEntity() = SimpleLocationDbEntity(
    id,
    name
)

fun List<CharacterDto>.toDomain() = map { it.toDomain() }

fun CharacterDto.toDomain() = Character(
    id,
    name,
    status,
    species,
    type,
    location,
    image,
    episodes
)

fun List<EpisodeResponse>.toEpisodeDomain() = map { it.toDomain() }

fun EpisodeResponse.toDomain() = Episode(
    id, name, date
)