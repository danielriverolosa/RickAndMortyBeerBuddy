package com.danielriverolosa.data.datasource.api

import com.danielriverolosa.data.datasource.api.character.model.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("api/character")
    suspend fun getCharacterList(
        @Query("page") page: Int
    ): Response<CharacterListResponse>
}