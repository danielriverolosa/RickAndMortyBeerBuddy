package com.danielriverolosa.data.datasource.api.location

import com.danielriverolosa.data.datasource.api.ApiClientGenerator
import com.danielriverolosa.data.datasource.api.RickAndMortyApi
import com.danielriverolosa.data.datasource.api.utils.handleResponse
import com.danielriverolosa.data.repository.mapper.toDomain
import com.danielriverolosa.domain.entity.Location
import com.danielriverolosa.domain.error.BadRequestError
import javax.inject.Inject

class LocationApiDataSource @Inject constructor(
    private val clientGenerator: ApiClientGenerator
) {

    suspend fun getLocation(id: Int): Location {
        val api = clientGenerator.generate(RickAndMortyApi::class)
        return api.getLocation(id).handleResponse { response ->
            response?.toDomain() ?: throw BadRequestError()
        }
    }
}