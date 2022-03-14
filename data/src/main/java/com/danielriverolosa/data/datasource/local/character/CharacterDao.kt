package com.danielriverolosa.data.datasource.local.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danielriverolosa.data.datasource.local.character.model.CharacterDbEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characterList: List<CharacterDbEntity>)

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterDbEntity>

    @Query("SELECT IFNULL(MAX(page),0) FROM character")
    suspend fun getLastPage(): Int

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterDbEntity?

}