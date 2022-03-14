package com.danielriverolosa.domain.interactor

import com.danielriverolosa.domain.Result
import com.danielriverolosa.domain.dispatcher.DefaultDispatcherProvider
import com.danielriverolosa.domain.dispatcher.DispatcherProvider
import com.danielriverolosa.domain.error.DomainException
import com.danielriverolosa.domain.failureOf
import com.danielriverolosa.domain.successOf
import kotlinx.coroutines.withContext

abstract class UseCase<Type, in Params>(
    private val dispatcher: DispatcherProvider = DefaultDispatcherProvider()
) where Type : Any {

    protected abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Result<Type> {
        return withContext(dispatcher.io) {
            try {
                successOf(run(params))
            } catch (e: Exception) {
                failureOf(DomainException(e.message.orEmpty()))
            }
        }
    }

    object None
}