package com.danielriverolosa.domain

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val error: Exception) : Result<Nothing>()
}

fun <T> successOf(value: T) = Result.Success(value)
fun failureOf(error: Exception) = Result.Failure(error)

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Exception) -> R
): R = when (this) {
    is Result.Success -> onSuccess(value)
    is Result.Failure -> onFailure(error)
}

fun <T> Result<T>.isSuccess(block: (T) -> Unit) {
    if (this is Result.Success<T>) {
        block(this.value)
    }
}

fun <T> Result<T>.isFailure(block: (Throwable) -> Unit) {
    if (this is Result.Failure) {
        block(this.error)
    }
}