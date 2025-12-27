package com.plana.products.feature.products.items.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.plana.products.core.navigation.Navigator
import com.plana.products.feature.products.details.api.ProductDetailsNavKey
import com.plana.products.feature.products.items.api.ProductsItemsNavKey

fun EntryProviderScope<NavKey>.productItemsEntry(navigator: Navigator) {
    entry<ProductsItemsNavKey> { navKey ->
        ProductsItemsScreen(
            navigateToProductDetails = { productID ->
                navigator.navigate(ProductDetailsNavKey(productID))
            }
        )
    }
}