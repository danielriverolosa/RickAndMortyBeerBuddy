package com.danielriverolosa.data.datasource.api.utils

import com.danielriverolosa.domain.error.BadRequestError
import retrofit2.Response

inline fun <reified T, R> Response<R>.handleResponse(block: (R?) -> T): T {
    if (isSuccessful) {
        return block(body())
    } else {
        throw BadRequestError()
    }
}