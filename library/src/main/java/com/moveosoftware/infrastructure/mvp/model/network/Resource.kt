package com.moveosoftware.infrastructure.mvp.model.network

/**
 * Created by eliran on 26/02/2018.
 *
 */
sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>()
}