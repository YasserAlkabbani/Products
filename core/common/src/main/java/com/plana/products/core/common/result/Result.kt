package com.plana.products.core.common.result

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

sealed interface RequestState<out T> {
    data class Success<T>(val data: T) : RequestState<T>
    data class Error(val exception: Throwable) : RequestState<Nothing>
    data object Loading : RequestState<Nothing>
}

fun <T> requestWithState(block: suspend () -> Unit): Flow<RequestState<T>> =
    flow<RequestState<T>> { RequestState.Success(block()) }
        .onStart { emit(RequestState.Loading) }
        .catch { emit(RequestState.Error(it)) }
        .flowOn(Dispatchers.Default)