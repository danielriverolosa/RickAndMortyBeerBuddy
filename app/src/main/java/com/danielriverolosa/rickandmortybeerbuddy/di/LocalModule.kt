package com.danielriverolosa.rickandmortybeerbuddy.di

import android.content.Context
import androidx.room.Room
import com.danielriverolosa.data.datasource.local.DbClientGenerator
import com.danielriverolosa.data.datasource.local.character.CharacterDao
import com.danielriverolosa.rickandmortybeerbuddy.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    
    @Singleton
    @Provides
    fun provideClientGenerator(@ApplicationContext context: Context): DbClientGenerator {
        return Room.databaseBuilder(
            context.applicationContext,
            DbClientGenerator::class.java,
            BuildConfig.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(clientGenerator: DbClientGenerator) : CharacterDao =
        clientGenerator.generateCharacterDao()
}