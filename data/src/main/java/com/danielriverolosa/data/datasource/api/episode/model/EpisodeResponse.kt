package com.danielriverolosa.data.datasource.api.episode.model

import com.squareup.moshi.Json

class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    @field:Json(name = "air_date") val date: String
)