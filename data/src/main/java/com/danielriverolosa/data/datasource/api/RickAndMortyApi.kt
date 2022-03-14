package com.danielriverolosa.data.datasource.api

import com.danielriverolosa.data.datasource.api.character.model.CharacterListResponse
import com.danielriverolosa.data.datasource.api.character.model.CharacterResponse
import com.danielriverolosa.data.datasource.api.character.model.LocationResponse
import com.danielriverolosa.data.datasource.api.episode.model.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("api/character")
    suspend fun getCharacterList(
        @Query("page") page: Int
    ): Response<CharacterListResponse>

    @GET("api/character/{ids}")
    suspend fun getCharacterList(
        @Path("ids") ids: List<Int>
    ): Response<List<CharacterResponse>>

    @GET("api/character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<CharacterListResponse>

    @GET("api/location/{id}")
    suspend fun getLocation(
        @Path("id") locationId: Int
    ): Response<LocationResponse>

    @GET("api/episode/{ids}")
    suspend fun getEpisodeList(
        @Path("ids") ids: List<Int>
    ): Response<List<EpisodeResponse>>
}