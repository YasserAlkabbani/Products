package com.plana.products.core.designsystem.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.icon.PIcon
import com.plana.products.core.designsystem.text.PText

@Composable
fun PIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {

    IconButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            PIcon(imageVector = imageVector)
        }
    )

}


@PPreviews
@Composable
fun PIconButtonPreview() {
    PBackground {
        PIconButton(
            imageVector = Icons.Default.Replay,
            onClick = {}
        )
    }
}