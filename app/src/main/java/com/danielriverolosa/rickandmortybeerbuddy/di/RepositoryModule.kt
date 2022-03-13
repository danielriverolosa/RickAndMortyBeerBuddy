package com.danielriverolosa.rickandmortybeerbuddy.di

import com.danielriverolosa.data.datasource.api.character.CharacterApiDataSource
import com.danielriverolosa.data.repository.CharacterRepositoryImpl
import com.danielriverolosa.domain.repository.CharacterRepository
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
    fun provideRepository(
        characterApiDataSource: CharacterApiDataSource
    ): CharacterRepository = CharacterRepositoryImpl(characterApiDataSource)
}