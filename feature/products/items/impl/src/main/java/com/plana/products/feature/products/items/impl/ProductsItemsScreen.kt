package com.plana.products.feature.products.items.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun  ProductsItemsScreen(
    navigateToProductDetails:(Long)-> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ){
        repeat(100) {index->
            Button(
                onClick = { navigateToProductDetails(index.toLong()) },
                content = {
                    Text("ITEM: $index")
                }
            )
        }
    }

}