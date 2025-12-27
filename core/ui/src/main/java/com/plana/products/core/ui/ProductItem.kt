package com.plana.products.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.card.PCard
import com.plana.products.core.designsystem.image.PImage
import com.plana.products.core.designsystem.text.PText
import com.plana.products.core.model.Product

@Composable
fun ProductItem(
    modifier: Modifier,
    product: Product,
    onClickItem: (Int) -> Unit,
) {

    PCard(
        modifier = modifier,
        content = {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                PImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .aspectRatio(1f),
                    imageUrl = product.image,
                    description = product.title
                )
                Spacer(modifier = Modifier.height(8.dp))
                PText(
                    modifier = Modifier,
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    minLines = 2,
                    maxLines = 2
                )
                PText(
                    modifier = Modifier,
                    text = product.price.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        onClick = { onClickItem(product.id) }
    )

}


@PPreviews
@Composable
fun ProductItemPreview() {
    PBackground {
        ProductItem(
            modifier = Modifier,
            product = Product(
                id = 0,
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95f,
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
                rating = 3.9f,
                ratingCount = 120,
            ),
            onClickItem = {}
        )
    }
}