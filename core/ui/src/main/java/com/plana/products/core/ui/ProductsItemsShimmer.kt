package com.plana.products.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ProductsItemsShimmer(itemCount: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(bottom = 36.dp)
    ) {
        items(
            count = itemCount,
            contentType = { "PRODUCTS" },
            itemContent = {
                ProductItem()
            }
        )
    }
}

@Composable
private fun ProductItem() {
    Box(
        modifier = Modifier
            .height(240.dp)
            .clip(MaterialTheme.shapes.medium)
            .shimmerEffect()
    ) { }
}

