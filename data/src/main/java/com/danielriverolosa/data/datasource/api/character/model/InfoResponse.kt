package com.danielriverolosa.data.datasource.api.character.model

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)