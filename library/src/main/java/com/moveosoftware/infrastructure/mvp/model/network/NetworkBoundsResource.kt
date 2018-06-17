package com.moveosoftware.infrastructure.mvp.model.network

import android.content.Context
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.exceptions.Exceptions
import java.io.IOException

/**
 * Created by eliran on 26/02/2018.
 *
 */
abstract class NetworkBoundResource<ResultType, RequestType>(context: Context) {

    private val result: Observable<Resource<ResultType>>

    init {
        // Lazy disk observable.
        val diskObservable = Observable.defer {
            loadFromDb()
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }

        // Lazy network observable.
        val networkObservable = Observable.defer {
            createCall()
                    .doOnNext { request ->
                        saveCallResult(request)
                    }
                    .onErrorReturn { throwable: Throwable ->
                        when (throwable) {
                            is HttpException -> {
                                throw Exceptions.propagate(NetworkExceptions.getNoServerConnectivityError(context))
                            }
                            is IOException -> {
                                throw Exceptions.propagate(NetworkExceptions.getNoNetworkConnectivityError(context))
                            }
                            else -> {
                                throw Exceptions.propagate(NetworkExceptions.getUnexpectedError(context))
                            }
                        }
                    }
                    .flatMap { loadFromDb() }
        }

        result = when {
            context.isNetworkStatusAvailable() -> networkObservable
                    .map<Resource<ResultType>> { Resource.Success(it) }
                    .onErrorReturn { Resource.Failure(it) }
                    // Read results in Android Main Thread (UI)
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(Resource.Loading())
            else -> diskObservable
                    .map<Resource<ResultType>> { Resource.Success(it) }
                    .onErrorReturn { Resource.Failure(it) }
                    // Read results in Android Main Thread (UI)
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(Resource.Loading())
        }
    }

    fun asFlowable(): Observable<Resource<ResultType>> {
        return result
    }


    protected abstract fun saveCallResult(request: RequestType)

    protected abstract fun loadFromDb(): Observable<ResultType>

    protected abstract fun createCall(): Observable<RequestType>
}