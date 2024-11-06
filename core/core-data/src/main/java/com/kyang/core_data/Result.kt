package com.kyang.core_data

/**
 * Result class for error handling in ui layer.
 * The ui will use the data if Result.Success, and throw an Error if Result.Error
 */
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
}