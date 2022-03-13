package com.danielriverolosa.data.datasource.api

import retrofit2.Retrofit
import kotlin.reflect.KClass

class ApiClientGenerator(private val retrofit: Retrofit) {
    fun <T : Any> generate(dataSource: KClass<T>): T = retrofit.create(dataSource.java)
}