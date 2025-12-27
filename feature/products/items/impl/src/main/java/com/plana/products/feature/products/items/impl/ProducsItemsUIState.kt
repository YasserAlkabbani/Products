package com.plana.products.feature.products.items.impl

sealed interface ProductsItemsState {

    data object Loading : ProductsItemsState
    data object Success : ProductsItemsState
    data object NoInternetConnection : ProductsItemsState
    data object TimeoutInternetConnection : ProductsItemsState
    data class ErrorWithMessage(val errorMessage: String?) : ProductsItemsState

}