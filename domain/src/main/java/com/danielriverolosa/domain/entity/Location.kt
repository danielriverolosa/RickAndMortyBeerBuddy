package com.danielriverolosa.domain.entity

data class Location(
    val id: Int,
    val name: String,
    val residents: List<Int> = emptyList()
)