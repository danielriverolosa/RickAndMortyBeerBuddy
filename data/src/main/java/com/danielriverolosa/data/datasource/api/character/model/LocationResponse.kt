package com.danielriverolosa.data.datasource.api.character.model

class LocationResponse(
    val name: String,
    val url: String,
    val residents: List<String>? = null
)