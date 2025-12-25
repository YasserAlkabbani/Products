package com.plana.products.feature.products.details.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.plana.products.core.navigation.Navigator
import com.plana.products.feature.products.details.api.ProductDetailsNavKey

fun EntryProviderScope<NavKey>.productDetailsEntry(navigator: Navigator) {
    entry<ProductDetailsNavKey> {
        ProductDetailsScreen(it.productID)
    }

}