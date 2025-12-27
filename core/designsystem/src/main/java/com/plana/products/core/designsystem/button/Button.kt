package com.plana.products.core.designsystem.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.plana.products.core.designsystem.PBackground
import com.plana.products.core.designsystem.PPreviews
import com.plana.products.core.designsystem.icon.PIcon
import com.plana.products.core.designsystem.text.PText

@Composable
fun PButton(
    modifier: Modifier = Modifier,
    text: String?,
    imageVector: ImageVector?,
    onClick: () -> Unit
) {

    FilledTonalButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            text?.let { PText(text = text) }
            imageVector?.let { PIcon(imageVector = imageVector) }
        }
    )

}


@PPreviews
@Composable
fun PButtonPreview() {
    PBackground {
        PButton(
            text = "Try Again",
            imageVector = Icons.Default.Replay,
            onClick = {}
        )
    }
}