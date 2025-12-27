package com.plana.products.feature.products.details.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plana.products.core.common.request.PError
import com.plana.products.core.common.request.RequestState
import com.plana.products.core.data.repository.products.ProductsRepository
import com.plana.products.core.model.Product
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = ProductDetailsViewModelFactory::class)
class ProductDetailsViewModel @AssistedInject constructor(
    private val productRepository: ProductsRepository,
    @Assisted val productID: Int,
) : ViewModel() {

    val productByID: StateFlow<Product?> = productRepository
        .getProductByID(productID)
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    private val _productsDetailsState: MutableStateFlow<ProductsDetailsState> =
        MutableStateFlow(ProductsDetailsState.Loading)
    val productsDetailsState: StateFlow<ProductsDetailsState> = _productsDetailsState
        .onStart { refreshProductDetails() }
        .stateIn(
            scope = viewModelScope,
            initialValue = ProductsDetailsState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    fun refreshProductDetails() {
        productRepository.refreshProductByID(productID)
            .onEach { requestState ->
                Log.d("TEST_PRODUCTS_ITEMS", "REFRESH_PRODUCT_DETAILS_STATE $requestState")
                when (requestState) {
                    is RequestState.Loading -> _productsDetailsState.update { ProductsDetailsState.Loading }
                    is RequestState.Success -> _productsDetailsState.update { ProductsDetailsState.Success }
                    is RequestState.Error -> _productsDetailsState.update {
                        when (val pError = requestState.pError) {
                            is PError.NoInternetConnection -> ProductsDetailsState.NoInternetConnection
                            is PError.ConnectionTimeout -> ProductsDetailsState.TimeoutInternetConnection
                            is PError.UnknownError ->
                                ProductsDetailsState.ErrorWithMessage(pError.throwable.message)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}

@AssistedFactory
internal interface ProductDetailsViewModelFactory {
    fun create(productID: Int): ProductDetailsViewModel
}