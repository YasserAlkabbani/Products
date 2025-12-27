package com.plana.products.feature.products.items.impl

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.plana.products.core.designsystem.chip.PFilterChip
import com.plana.products.core.designsystem.text.PText
import com.plana.products.core.model.Product
import com.plana.products.core.ui.PErrorMessage
import com.plana.products.core.ui.ProductItem
import com.plana.products.core.ui.ProductsItemsShimmer

@Composable
fun ProductsItemsScreen(
    navigateToProductDetails: (Int) -> Unit,
    productsItemsViewModel: ProductsItemsViewModel = hiltViewModel()
) {

    val productsLazyPagingItems =
        productsItemsViewModel.productsPagingData.collectAsLazyPagingItems()

    val productsItemState: ProductsItemsState =
        productsItemsViewModel.productsItemsState.collectAsStateWithLifecycle().value

    val productsCategories: List<String> =
        productsItemsViewModel.productsCategories.collectAsStateWithLifecycle().value

    val selectedCategory: String? =
        productsItemsViewModel.selectedCategory.collectAsStateWithLifecycle().value

    ProductsItemsScreen(
        productsItemState = productsItemState,
        productsLazyPagingItems = productsLazyPagingItems,
        productsCategories = productsCategories,
        selectedCategory = selectedCategory,
        refreshProductsItems = productsItemsViewModel::refreshProducts,
        onSelectCategory = productsItemsViewModel::selectCategory,
        navigateToProductDetails = navigateToProductDetails,
    )

}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProductsItemsScreen(
    productsItemState: ProductsItemsState,
    productsLazyPagingItems: LazyPagingItems<Product>,
    productsCategories: List<String>,
    selectedCategory: String?,
    refreshProductsItems: () -> Unit,
    onSelectCategory: (String?) -> Unit,
    navigateToProductDetails: (Int) -> Unit,
) {


    val isEmptyList = productsLazyPagingItems.itemSnapshotList.isEmpty()

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            PFilterChip(
                label = stringResource(R.string.all),
                selected = selectedCategory == null,
                onSelect = { onSelectCategory(null) },
            )
            productsCategories.forEach { category ->
                PFilterChip(
                    label = category,
                    selected = selectedCategory == category,
                    onSelect = onSelectCategory,
                )
            }
        }
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = productsItemState is ProductsItemsState.Loading,
            onRefresh = refreshProductsItems,
        ) {
            Column(Modifier.fillMaxSize()) {
                AnimatedContent(
                    modifier = Modifier.fillMaxWidth(),
                    targetState = productsItemState,
                    contentAlignment = Alignment.TopCenter,
                    content = { productsItemState ->
                        when (productsItemState) {
                            is ProductsItemsState.ErrorWithMessage ->
                                PErrorMessage(
                                    errorTitle = stringResource(R.string.ops_theres_something_wrong),
                                    errorSubtitle = productsItemState.errorMessage
                                        ?: stringResource(R.string.unknown_error),
                                    buttonText = stringResource(R.string.try_again),
                                    onRetry = refreshProductsItems
                                )

                            is ProductsItemsState.TimeoutInternetConnection ->
                                PErrorMessage(
                                    errorTitle = stringResource(R.string.slow_internet_connection),
                                    errorSubtitle = stringResource(R.string.make_sure_to_have_a_good_internet_connection),
                                    buttonText = stringResource(R.string.try_again),
                                    onRetry = refreshProductsItems
                                )

                            is ProductsItemsState.NoInternetConnection ->
                                PErrorMessage(
                                    errorTitle = stringResource(R.string.connection_issue),
                                    errorSubtitle = stringResource(R.string.please_check_your_internet_connection),
                                    buttonText = stringResource(R.string.try_again),
                                    onRetry = refreshProductsItems
                                )

                            ProductsItemsState.Loading -> LinearWavyProgressIndicator(
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                                    .fillMaxWidth()
                            )

                            ProductsItemsState.Success -> Unit
                        }
                    }
                )
                AnimatedContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = if (isLandscape) 96.dp else 8.dp),
                    targetState = isEmptyList,
                    contentAlignment = Alignment.TopCenter,
                    content = { isEmpty ->
                        when (isEmpty) {
                            true -> when (productsItemState) {
                                ProductsItemsState.Loading -> ProductsItemsShimmer(8)
                                else -> Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    content = {
                                        PText(
                                            text = stringResource(R.string.ops_theres_no_products_to_show),
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }
                                )
                            }

                            false -> LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                contentPadding = PaddingValues(bottom = 36.dp)
                            ) {
                                items(
                                    count = productsLazyPagingItems.itemCount,
                                    key = productsLazyPagingItems.itemKey { it.id },
                                    contentType = { "PRODUCTS" },
                                    itemContent = { index ->
                                        productsLazyPagingItems[index]?.let { product ->
                                            ProductItem(
                                                modifier = Modifier
                                                    .animateItem()
                                                    .animateEnterExit(),
                                                onClickItem = navigateToProductDetails,
                                                product = product,
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}