package com.plana.products.core.common.request

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface PError {
    data object NoInternetConnection : PError
    data object ConnectionTimeout : PError
    data class UnknownError(val throwable: Throwable) : PError
}

sealed interface RequestState<out T> {
    data class Success<T>(val data: T) : RequestState<T>
    data class Error(val pError: PError) : RequestState<Nothing>
    data object Loading : RequestState<Nothing>
}

fun <T> requestWithState(block: suspend () -> T): Flow<RequestState<T>> =
    flow<RequestState<T>> { emit(RequestState.Success(block())) }
        .onStart { emit(RequestState.Loading) }
        .catch {
            when (it) {
                is UnknownHostException -> emit(RequestState.Error(PError.NoInternetConnection))
                is SocketTimeoutException -> emit(RequestState.Error(PError.ConnectionTimeout))
                else -> emit(RequestState.Error(PError.UnknownError(it)))
            }
        }
        .flowOn(Dispatchers.Default)