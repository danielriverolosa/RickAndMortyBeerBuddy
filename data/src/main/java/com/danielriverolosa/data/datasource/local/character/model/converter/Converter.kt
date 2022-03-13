package com.danielriverolosa.data.datasource.local.character.model.converter

import androidx.room.TypeConverter
import com.danielriverolosa.data.datasource.local.character.model.SimpleLocationDbEntity
import com.danielriverolosa.domain.entity.Status
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class StatusConverter {
    @TypeConverter
    fun toStatus(value: String) = enumValueOf<Status>(value)

    @TypeConverter
    fun fromStatus(value: Status) = value.name
}

class ListIntConverter {
    @TypeConverter
    fun fromString(list: String): List<Int>? = Moshi.Builder().build()
        .adapter<List<Int>>(Types.newParameterizedType(List::class.java, Int::class.javaObjectType))
        .fromJson(list)

    @TypeConverter
    fun toList(list: List<Int>?): String? = Moshi.Builder().build()
        .adapter<List<Int>>(Types.newParameterizedType(List::class.java, Int::class.javaObjectType))
        .toJson(list)
}

class SimpleLocationConverter {
    @TypeConverter
    fun fromString(entity: String): SimpleLocationDbEntity? = Moshi.Builder().build()
        .adapter(SimpleLocationDbEntity::class.java)
        .fromJson(entity)

    @TypeConverter
    fun toEntity(entity: SimpleLocationDbEntity?): String? = Moshi.Builder().build()
        .adapter(SimpleLocationDbEntity::class.java)
        .toJson(entity)
}