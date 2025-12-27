package com.plana.products.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews

@Composable
fun PIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector
) {

    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = null
    )

}

@PPreviews
@Composable
fun PIconPreview() {
    PBackground {
        PIcon(imageVector = Icons.Default.Error)
    }
}