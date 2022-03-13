package com.danielriverolosa.domain.error

class HttpException(val code: Int, override val message: String?) : RuntimeException(message)

class BadRequestError : RuntimeException()

class NetworkError : RuntimeException()

class RepositoryException(override val message: String) : RuntimeException(message)

class DomainException(override val message: String) : RuntimeException(message)