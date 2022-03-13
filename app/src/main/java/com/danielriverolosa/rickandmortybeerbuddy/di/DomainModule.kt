package com.danielriverolosa.rickandmortybeerbuddy.di

import com.danielriverolosa.domain.interactor.character.GetCharacterListUseCase
import com.danielriverolosa.domain.repository.CharacterRepository
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
    fun provideRepository(
        repository: CharacterRepository
    ): GetCharacterListUseCase = GetCharacterListUseCase(repository)

}