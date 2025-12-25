package com.plana.products.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.plana.products.core.navigation.Navigator
import com.plana.products.core.navigation.asEntries
import com.plana.products.core.navigation.rememberNavigationState
import com.plana.products.feature.products.details.impl.productDetailsEntry
import com.plana.products.feature.products.items.api.ProductsItemsNavKey
import com.plana.products.feature.products.items.impl.productItemsEntry

@Composable
fun PApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        val navigationState = rememberNavigationState(ProductsItemsNavKey)
        val navigator = remember { Navigator(navigationState) }
        val entryProvider = entryProvider {
            productItemsEntry(navigator)
            productDetailsEntry(navigator)
        }

        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            onBack = { navigator.goBack() },
            entries = navigationState.asEntries(entryProvider)
        )
    }

}