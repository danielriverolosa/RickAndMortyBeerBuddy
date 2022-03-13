package com.danielriverolosa.data.datasource.local.character.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielriverolosa.domain.entity.Status

@Entity(tableName = "character")
class CharacterDbEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val location: SimpleLocationDbEntity,
    val image: String,
    val episodes: List<Int>,
    val page: Int
)