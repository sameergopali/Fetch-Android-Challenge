package com.fetch.project.network

/**
 * A sealed class representing the result of a network operation.
 *
 * @param T The type of data returned in the successful result.
 */
sealed class NetworkResult<out T> {
    /**
     * Represents a successful result of a network operation.
     *
     * @param data The data returned from the network operation.
     * @param T The type of the data.
     */
    data class Success<T>(val data: T) : NetworkResult<T>()

    /**
     * Represents an error result of a network operation.
     *
     * @param message The error message.
     */
    data class Error(val message: String) : NetworkResult<Nothing>()
}