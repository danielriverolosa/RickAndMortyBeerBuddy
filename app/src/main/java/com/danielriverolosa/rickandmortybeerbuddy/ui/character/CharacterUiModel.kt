package com.danielriverolosa.rickandmortybeerbuddy.ui.character

import android.os.Parcelable
import com.danielriverolosa.domain.entity.Character
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUiModel(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable

fun Character.toUiModel() = CharacterUiModel(
    id, name, image
)