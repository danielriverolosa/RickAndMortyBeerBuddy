package com.danielriverolosa.rickandmortybeerbuddy.di

import com.danielriverolosa.data.datasource.api.character.CharacterApiDataSource
import com.danielriverolosa.data.datasource.api.episode.EpisodeApiDataSource
import com.danielriverolosa.data.datasource.api.location.LocationApiDataSource
import com.danielriverolosa.data.datasource.local.character.CharacterLocalDataSource
import com.danielriverolosa.data.repository.CharacterRepositoryImpl
import com.danielriverolosa.data.repository.EpisodeRepositoryImpl
import com.danielriverolosa.data.repository.LocationRepositoryImpl
import com.danielriverolosa.domain.repository.CharacterRepository
import com.danielriverolosa.domain.repository.EpisodeRepository
import com.danielriverolosa.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterApiDataSource: CharacterApiDataSource,
        localDataSource: CharacterLocalDataSource
    ): CharacterRepository = CharacterRepositoryImpl(characterApiDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideLocationRepository(
        apiDataSource: LocationApiDataSource
    ): LocationRepository = LocationRepositoryImpl(apiDataSource)

    @Singleton
    @Provides
    fun provideEpisodeRepository(
        apiDataSource: EpisodeApiDataSource
    ): EpisodeRepository = EpisodeRepositoryImpl(apiDataSource)
}