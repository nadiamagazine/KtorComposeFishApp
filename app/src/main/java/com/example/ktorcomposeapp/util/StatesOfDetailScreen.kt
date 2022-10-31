package com.example.ktorcomposeapp.util

sealed class StatesOfDetailScreen<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : StatesOfDetailScreen<T>(data)

    class Error<T>(message: String, data: T? = null) : StatesOfDetailScreen<T>(data, message)

    class Loading<T> : StatesOfDetailScreen<T>()
}
