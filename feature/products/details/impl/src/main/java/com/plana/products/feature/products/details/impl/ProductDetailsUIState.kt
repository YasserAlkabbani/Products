package com.plana.products.feature.products.details.impl


sealed interface ProductsDetailsState {
    data object Loading : ProductsDetailsState
    data object Success : ProductsDetailsState
    data object NoInternetConnection : ProductsDetailsState
    data object TimeoutInternetConnection : ProductsDetailsState
    data class ErrorWithMessage(val errorMessage: String?) : ProductsDetailsState
}
