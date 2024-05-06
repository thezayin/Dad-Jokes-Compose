package com.thezayin.framework.utils

/**
 * A generic monad that can be either a success, loading or an error.
 * */
sealed interface ResourceCompat<out T> {
    object Loading : ResourceCompat<Nothing>
    data class Success<out T>(val data: T) : ResourceCompat<T>
    data class Error(val throwable: Throwable) : ResourceCompat<Nothing>
}

fun <T> T.toSuccess(): ResourceCompat<T> = ResourceCompat.Success(this)
fun <T> Throwable.toError(): ResourceCompat<T> = ResourceCompat.Error(this)

/**
 * Performs the [transform] action on data and returns a new [ResourceCompat] with the same state but new data
 */
fun <T, R> ResourceCompat<T>.transform(
    transform: ((value: T) -> R)
): ResourceCompat<R> = when (this) {
    is ResourceCompat.Loading -> ResourceCompat.Loading
    is ResourceCompat.Success -> ResourceCompat.Success(transform(data))
    is ResourceCompat.Error -> ResourceCompat.Error(throwable)
}

fun <T> ResourceCompat<T>.getOrNull(): T? = when (this) {
    is ResourceCompat.Loading -> null
    is ResourceCompat.Success -> data
    is ResourceCompat.Error -> null
}

fun <T> ResourceCompat<T>.getOrThrow(): T = when (this) {
    is ResourceCompat.Loading -> throw IllegalStateException("Resource is loading")
    is ResourceCompat.Success -> data
    is ResourceCompat.Error -> throw throwable
}