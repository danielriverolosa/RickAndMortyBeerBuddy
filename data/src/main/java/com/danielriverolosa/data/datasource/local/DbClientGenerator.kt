package com.danielriverolosa.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielriverolosa.data.datasource.local.character.CharacterDao
import com.danielriverolosa.data.datasource.local.character.model.CharacterDbEntity
import com.danielriverolosa.data.datasource.local.character.model.converter.ListIntConverter
import com.danielriverolosa.data.datasource.local.character.model.converter.SimpleLocationConverter
import com.danielriverolosa.data.datasource.local.character.model.converter.StatusConverter

@Database(entities = [CharacterDbEntity::class], version = 1)
@TypeConverters(StatusConverter::class, ListIntConverter::class, SimpleLocationConverter::class)
abstract class DbClientGenerator : RoomDatabase() {
    abstract fun generateCharacterDao(): CharacterDao
}