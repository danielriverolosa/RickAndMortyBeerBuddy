package com.danielriverolosa.rickandmortybeerbuddy.di

import com.danielriverolosa.domain.interactor.buddy.GetBuddyBeerUseCase
import com.danielriverolosa.domain.interactor.character.GetCharacterListUseCase
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
object DomainModule {

    @Singleton
    @Provides
    fun provideGetCharacterListUseCase(
        repository: CharacterRepository
    ): GetCharacterListUseCase = GetCharacterListUseCase(repository)

    @Singleton
    @Provides
    fun provideGetBuddyBeerUseCase(
        characterRepository: CharacterRepository,
        locationRepository: LocationRepository,
        episodeRepository: EpisodeRepository
    ): GetBuddyBeerUseCase = GetBuddyBeerUseCase(characterRepository, locationRepository, episodeRepository)

}