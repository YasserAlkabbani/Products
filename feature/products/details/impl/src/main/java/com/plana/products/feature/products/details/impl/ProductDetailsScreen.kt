package com.plana.products.feature.products.details.impl

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plana.products.core.designsystem.button.PIconButton
import com.plana.products.core.designsystem.card.PCardContainer
import com.plana.products.core.designsystem.image.PImage
import com.plana.products.core.designsystem.progress.PLinearProgress
import com.plana.products.core.designsystem.text.PText
import com.plana.products.core.model.Product
import com.plana.products.core.ui.PErrorConnectionIssue
import com.plana.products.core.ui.PErrorConnectionTimeout
import com.plana.products.core.ui.PErrorUnexpected
import com.plana.products.core.ui.PRating

@Composable
fun ProductDetailsScreen(
    productDetailsViewModel: ProductDetailsViewModel,
    onClickBack: () -> Unit
) {
    val product = productDetailsViewModel.productByID.collectAsStateWithLifecycle().value

    val productDetailsState =
        productDetailsViewModel.productsDetailsState.collectAsStateWithLifecycle().value

    ProductDetailsScreen(
        product = product,
        productsDetailsState = productDetailsState,
        refreshProductDetails = productDetailsViewModel::refreshProductDetails,
        onClickBack = onClickBack
    )
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProductDetailsScreen(
    product: Product?,
    productsDetailsState: ProductsDetailsState,
    refreshProductDetails: () -> Unit,
    onClickBack: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(Modifier.fillMaxSize()) {
        AnimatedContent(
            modifier = Modifier.fillMaxWidth(),
            targetState = productsDetailsState,
            contentAlignment = Alignment.TopCenter,
            content = { productsDetailsState ->
                when (productsDetailsState) {
                    is ProductsDetailsState.Loading -> PLinearProgress()
                    is ProductsDetailsState.Success -> Unit
                    is ProductsDetailsState.TimeoutInternetConnection ->
                        PErrorConnectionTimeout(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            onRetry = refreshProductDetails
                        )

                    is ProductsDetailsState.NoInternetConnection ->
                        PErrorConnectionIssue(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            onRetry = refreshProductDetails
                        )

                    is ProductsDetailsState.ErrorWithMessage -> PErrorUnexpected(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onRetry = refreshProductDetails
                    )

                }
            }
        )
        Box(
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {
            when (isLandscape) {
                true -> AnimatedContent(targetState = product) { product ->
                    when (product) {
                        null -> Unit
                        else -> ProductDetailsLandscape(product)
                    }
                }

                false -> AnimatedContent(targetState = product) { product ->
                    when (product) {
                        null -> Unit
                        else -> ProductDetailsPortrait(product)
                    }
                }
            }

            PIconButton(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.ArrowBackIosNew,
                onClick = onClickBack
            )
        }
    }


}

@Composable
fun ProductDetailsPortrait(product: Product) {
    Column(
        Modifier.fillMaxSize()
    ) {
        PCardContainer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            content = {
                PImage(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxSize(),
                    imageUrl = product.image,
                    description = product.title
                )
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            PText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = product.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                PCardContainer {
                    PText(
                        modifier = Modifier,
                        text = stringResource(R.string.price, product.price),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                PCardContainer {
                    PRating(
                        rating = product.rating,
                        ratingCount = product.ratingCount
                    )
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
            PText(
                modifier = Modifier.fillMaxWidth(),
                text = product.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
fun ProductDetailsLandscape(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 6.dp)
    ) {
        Row {
            PCardContainer(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                content = {
                    PImage(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxSize(),
                        imageUrl = product.image,
                        description = product.title
                    )
                },
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                PText(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    PCardContainer {
                        PText(
                            modifier = Modifier,
                            text = stringResource(R.string.price, product.price),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    PCardContainer {
                        PRating(
                            rating = product.rating,
                            ratingCount = product.ratingCount
                        )
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
                PText(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.description + product.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

