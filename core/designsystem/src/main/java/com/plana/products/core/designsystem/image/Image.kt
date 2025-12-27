package com.plana.products.core.designsystem.image

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.icon.PIcon

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PImage(
    modifier: Modifier,
    imageUrl: String,
    description: String
) {
    val painter = rememberAsyncImagePainter(imageUrl)
    val state by painter.state.collectAsState()

    when (state) {
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading ->
            ContainedLoadingIndicator(
                modifier = modifier.padding(16.dp)
            )

        is AsyncImagePainter.State.Error -> PIcon(
            modifier = modifier.padding(16.dp),
            imageVector = Icons.Default.Error
        )

        is AsyncImagePainter.State.Success -> Image(
            modifier = modifier,
            painter = painter,
            contentDescription = description
        )
    }

}

@PPreviews
@Composable

fun PImagePreview() {
    PBackground {
        PImage(
            modifier = Modifier,
            imageUrl = "https://www.google.com/images/branding/googlelogo",
            description = "Google"
        )
    }
}