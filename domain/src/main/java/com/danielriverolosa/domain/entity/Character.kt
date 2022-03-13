package com.danielriverolosa.domain.entity

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val location: Location,
    val image: String,
    val episodes: List<Int>
)