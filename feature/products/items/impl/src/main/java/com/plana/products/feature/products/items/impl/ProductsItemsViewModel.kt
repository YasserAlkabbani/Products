package com.plana.products.feature.products.items.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.plana.products.core.common.request.PError
import com.plana.products.core.common.request.RequestState
import com.plana.products.core.data.repository.products.OfflineFirstProductsRepository
import com.plana.products.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProductsItemsViewModel @Inject constructor(
    private val productsRepository: OfflineFirstProductsRepository
) : ViewModel() {

    val productsCategories: StateFlow<List<String>> = productsRepository
        .getProductsCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    private val _selectedCategory: MutableStateFlow<String?> = MutableStateFlow(null)
    val selectedCategory: MutableStateFlow<String?> = _selectedCategory
    fun selectCategory(category: String?) = _selectedCategory.update { category }

    val productsPagingData: Flow<PagingData<Product>> = selectedCategory
        .flatMapLatest { productsRepository.getProductsByCategory(it) }

    private val _productsItemsState: MutableStateFlow<ProductsItemsState> =
        MutableStateFlow(ProductsItemsState.Loading)
    val productsItemsState: StateFlow<ProductsItemsState> = _productsItemsState
        .onStart { refreshProducts() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductsItemsState.Loading
        )

    fun refreshProducts() {
        productsRepository
            .refreshProducts()
            .onEach { requestState ->
                Log.d("TEST_PRODUCTS_ITEMS", "REFRESH START ON_EACH $requestState")
                when (requestState) {
                    is RequestState.Loading -> _productsItemsState.update { ProductsItemsState.Loading }
                    is RequestState.Success -> _productsItemsState.update { ProductsItemsState.Success }
                    is RequestState.Error -> _productsItemsState.update {
                        when (val pError = requestState.pError) {
                            is PError.NoInternetConnection -> ProductsItemsState.NoInternetConnection
                            is PError.ConnectionTimeout -> ProductsItemsState.TimeoutInternetConnection
                            is PError.UnknownError ->
                                ProductsItemsState.ErrorWithMessage(pError.throwable.message)
                        }

                    }

                }
            }
            .launchIn(viewModelScope)
    }

}