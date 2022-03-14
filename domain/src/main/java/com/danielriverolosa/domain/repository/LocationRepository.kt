package com.danielriverolosa.domain.repository

interface LocationRepository {
    suspend fun getCharacterIdsFromLocation(locationId: Int): List<Int>
}