package com.danielriverolosa.data.repository

import com.danielriverolosa.data.datasource.api.location.LocationApiDataSource
import com.danielriverolosa.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiDataSource: LocationApiDataSource
): LocationRepository {

    override suspend fun getCharacterIdsFromLocation(locationId: Int): List<Int> {
        val location = apiDataSource.getLocation(locationId)
        return location.residents
    }
}